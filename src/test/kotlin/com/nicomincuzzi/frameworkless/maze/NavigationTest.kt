package com.nicomincuzzi.frameworkless.maze

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class NavigationTest {
    @Test
    fun showResultRetroRoutePuzzle() {
        val navigation = Navigation(emptyList(), listOf(Room()))
        navigation.showResultRetroRoutePuzzle(HashMap())
    }

    @Test
    fun oneItemsIsFoundWithSuccess() {
        val findingItems = listOf("Knife")
        val navigation = Navigation(findingItems, listOf(Room()))
        val roomMaze = Room()
        val utensil = Utensil()
        utensil.name = "Knife"
        roomMaze.objects = listOf(utensil)

        val result = navigation.searchItemsMaze(roomMaze)
        val expectedGameResult = result.entries.iterator().next().value
        Assertions.assertTrue(expectedGameResult.items.contains("Knife"))
    }

    @Test
    fun noItemsIsFound() {
        val navigation = Navigation(emptyList(), listOf(Room()))
        val roomMaze = Room()
        val utensil = Utensil()
        utensil.name = "Knife"
        roomMaze.objects = listOf(utensil)

        val result = navigation.searchItemsMaze(roomMaze)
        val expectedGameResult = result.entries.iterator().next().value
        Assertions.assertTrue(expectedGameResult.items.contains("None"))
    }
}