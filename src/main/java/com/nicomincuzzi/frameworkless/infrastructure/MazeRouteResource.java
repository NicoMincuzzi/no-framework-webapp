package com.nicomincuzzi.frameworkless.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicomincuzzi.frameworkless.configuration.StringHandler;
import com.nicomincuzzi.frameworkless.domain.GameResult;
import com.nicomincuzzi.frameworkless.domain.JsonManagerMaze;
import com.nicomincuzzi.frameworkless.domain.MazeMap;
import com.nicomincuzzi.frameworkless.domain.PlayState;
import com.nicomincuzzi.frameworkless.domain.Room;
import com.nicomincuzzi.frameworkless.domain.repository.MazeMapRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MazeRouteResource extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> bodyRequest = getBodyRequest(req.getReader());
        String roomNumber = (String) bodyRequest.get("id_room");
        List<String> objsCollect = (List<String>) bodyRequest.get("objects");

        List<String> findingItems = new ArrayList<>();
        findingItems.addAll(objsCollect);

        MazeMap mazeMap = new MazeMapRepository().retrieve("map.json");
        JsonManagerMaze jsonMngMaze = new JsonManagerMaze();
        Room roomMaze = retrieveRoomMaze(Integer.parseInt(roomNumber), mazeMap, jsonMngMaze);
        PlayState playState = new PlayState(findingItems, jsonMngMaze, roomMaze, new GameResult());
        Map<String, GameResult> foundItems = playState.execute();

        PrintWriter outResponse = null;
        try {
            resp.setContentType("application/json");
            outResponse = resp.getWriter();
            outResponse.println(setBodyResponse(foundItems));
        } catch (IOException e) {
            log.error("Error in sent response: ", e);
        } finally {
            outResponse.flush();
        }
    }

    private <T> Map<String, T> getBodyRequest(BufferedReader bodyRequest) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = bodyRequest.readLine()) != null) {
            sb.append(line).append("\n");
        }

        bodyRequest.close();
        return new ObjectMapper().readValue(sb.toString(), Map.class);
    }

    public Room retrieveRoomMaze(int roomNumber, MazeMap mazeMap, JsonManagerMaze jsonMngMaze) {
        Room roomMaze = jsonMngMaze.getRoomById(roomNumber, mazeMap);

        if (roomMaze == null) {
            log.warn("Please insert a valid room number!");
            return null;
        }
        return roomMaze;
    }

    private Map<String, List<Map<String, String>>> setBodyResponse(Map<String, GameResult> resultOutput) {
        Map<String, List<Map<String, String>>> objResult = new HashMap<>();
        List<Map<String, String>> listResult = new ArrayList<>();

        for (String idStepRoute : resultOutput.keySet()) {
            String items = "";

            for (String item : resultOutput.get(idStepRoute).getItems()) {
                items = item.concat(",");
            }

            items = StringHandler.getInstance().removeLastComma(items);

            //create each JSONObject entry
            Map<String, String> objEntry = new HashMap<>();
            objEntry.put("id", String.valueOf(resultOutput.get(idStepRoute).getId()));
            objEntry.put("name", resultOutput.get(idStepRoute).getRoom());
            objEntry.put("objects", items);

            listResult.add(objEntry);
        }

        objResult.put("result", listResult);
        return objResult;
    }
}