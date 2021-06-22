package com.nicomincuzzi.frameworkless.maze

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class NavigationTest {

    @Test
    fun showResultRetroRoutePuzzle() {
        val navigation = Navigation(emptyList(), listOf(Room()), mock(JsonManagerMaze::class.java))
        navigation.showResultRetroRoutePuzzle(HashMap())
    }

    @Test
    fun oneItemsIsFoundWithSuccess() {
        val findingItems = listOf("Knife")
        val navigation = Navigation(findingItems, listOf(Room()), mock(JsonManagerMaze::class.java))
        val utensil = Utensil(name = "Knife")
        val roomMaze = Room(objects = listOf(utensil))

        val result = navigation.searchItemsMaze(roomMaze)
        val expectedGameResult = result.entries.iterator().next().value
        Assertions.assertTrue(expectedGameResult.items.contains("Knife"))
    }

    @Test
    fun noItemsIsFound() {
        val navigation = Navigation(emptyList(), listOf(Room()), mock(JsonManagerMaze::class.java))
        val utensil = Utensil(name = "Knife")
        val roomMaze = Room(objects = listOf(utensil))

        val result = navigation.searchItemsMaze(roomMaze)
        val expectedGameResult = result.entries.iterator().next().value
        Assertions.assertTrue(expectedGameResult.items.contains("None"))
    }
}