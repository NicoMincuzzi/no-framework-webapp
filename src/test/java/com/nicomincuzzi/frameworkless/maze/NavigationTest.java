package com.nicomincuzzi.frameworkless.maze;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NavigationTest {

    @Test
    void showResultRetroRoutePuzzle() {

        Navigation navigation = new Navigation(emptyList(), singletonList(new Room()));

        navigation.showResultRetroRoutePuzzle(new HashMap<>());
    }

    @Test
    void oneItemsIsFoundWithSuccess() {
        List<String> findingItems = singletonList("Knife");
        Navigation navigation = new Navigation(findingItems, singletonList(new Room()));

        Room roomMaze = new Room();
        Utensil utensil = new Utensil();
        utensil.setName("Knife");
        roomMaze.setObjects(singletonList(utensil));

        Map<String, GameResult> result = navigation.searchItemsMaze(roomMaze);

        GameResult expectedGameResult = result.entrySet().iterator().next().getValue();
        assertTrue(expectedGameResult.getItems().contains("Knife"));
    }

    @Test
    void noItemsIsFound() {
        Navigation navigation = new Navigation(emptyList(), singletonList(new Room()));

        Room roomMaze = new Room();
        Utensil utensil = new Utensil();
        utensil.setName("Knife");
        roomMaze.setObjects(singletonList(utensil));

        Map<String, GameResult> result = navigation.searchItemsMaze(roomMaze);

        GameResult expectedGameResult = result.entrySet().iterator().next().getValue();
        assertTrue(expectedGameResult.getItems().contains("None"));
    }
}