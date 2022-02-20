package com.nicomincuzzi.frameworkless.domain

import com.nicomincuzzi.frameworkless.domain.repository.MazeMapRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

internal class PlayStateTest {
    @Test
    fun oneItemIsFoundWithSuccessStartByDiningRoom() {
        val gameResult = Mockito.mock(GameResult::class.java)
        val jsonMngMaze = JsonManagerMaze()
        jsonMngMaze.getRoomById(9, MazeMapRepository().retrieve("map.json"))
        val rooms = jsonMngMaze.arrayRooms

        val playState = PlayState(listOf("Knife"), jsonMngMaze, rooms[1], gameResult)
        val execute = playState.execute()

        Assertions.assertEquals(execute.size, 7)
        Assertions.assertTrue(execute.values.stream().anyMatch { it.items.contains("Knife") })
    }

    @Test
    fun oneItemIsFoundWithSuccessStartByKitchen() {
        val gameResult = Mockito.mock(GameResult::class.java)
        val jsonMngMaze = JsonManagerMaze()
        jsonMngMaze.getRoomById(9, MazeMapRepository().retrieve("map.json"))
        val rooms = jsonMngMaze.arrayRooms

        val playState = PlayState(listOf("Knife"), jsonMngMaze, rooms[2], gameResult)
        val execute = playState.execute()

        Assertions.assertEquals(7, execute.size)
        Assertions.assertTrue(execute.values.stream().anyMatch { it.items.contains("Knife") })

    }

    @Test
    fun noItemsIsFound() {
        val gameResult = Mockito.mock(GameResult::class.java)
        val jsonMngMaze = JsonManagerMaze()
        jsonMngMaze.getRoomById(9, MazeMapRepository().retrieve("map.json"))
        val rooms = jsonMngMaze.arrayRooms

        val playState = PlayState(listOf("Spoon"), jsonMngMaze, rooms[1], gameResult)
        val execute = playState.execute()

        Assertions.assertEquals(7, execute.size)
        Assertions.assertFalse(execute.values.stream().anyMatch { it.items.contains("Knife") })
    }
}