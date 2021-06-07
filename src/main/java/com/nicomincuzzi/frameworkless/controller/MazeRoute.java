package com.nicomincuzzi.frameworkless.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicomincuzzi.frameworkless.maze.*;
import com.nicomincuzzi.frameworkless.utils.StringHandler;
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
public class MazeRoute extends HttpServlet {

    private static final String JSON_MAP = "map.json";

    private Navigation navMap;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> bodyRequest = getBodyRequest(req.getReader());
        String roomNumber = (String) bodyRequest.get("id_room");
        List<String> objsCollect = (List<String>) bodyRequest.get("objects");

        List<String> findingItems = new ArrayList<>();

        findingItems.addAll(objsCollect);

        Map<String, GameResult> foundItems = new HashMap<>();
        try {
            foundItems = runMazeRoutePuzzleWebApp(Integer.parseInt(roomNumber), findingItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json");

        //create a JSON bodyRequest response
        Map<String, List<Map<String, String>>> response = setBodyResponse(foundItems);

        // Allocate a output writer to write the response message into the network socket
        PrintWriter outResponse = null;

        // Write the response message, in an HTML page
        try {
            outResponse = resp.getWriter();
            outResponse.println(response);
        } catch (IOException e) {
            log.error("Error in sent response: ", e);
        } finally {
            outResponse.flush();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

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

    private Map<String, GameResult> runMazeRoutePuzzleWebApp(int roomNumber, List<String> findingItems) {
        JsonManagerMaze jsonMngMaze = new JsonManagerMaze();
        navMap = new Navigation(findingItems, jsonMngMaze.getArrayRooms());

        MazeMap mazeMap = new MazeMap().retrieve(JSON_MAP);
        Room roomMaze = jsonMngMaze.getRoomById(roomNumber, mazeMap);
        return navMap.searchItemsMaze(roomMaze);
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