package com.nicomincuzzi.frameworkless.maze.fsm

import com.nicomincuzzi.frameworkless.maze.JsonManagerMaze
import com.nicomincuzzi.frameworkless.maze.ManagerMaze
import com.nicomincuzzi.frameworkless.maze.Room
import com.nicomincuzzi.frameworkless.maze.Utensil
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*


class PlayStateTest {

    @Test
    fun winInPlayState() {
        val room = Room(objects = listOf(Utensil(name = "Knife")))
        val jsonManagerMaze = mock(JsonManagerMaze::class.java)
        val managerMaze = mock(ManagerMaze::class.java)
        `when`(jsonManagerMaze.arrayRooms).thenReturn(listOf(Room()))

        val playState = PlayState(listOf("Knife"), jsonManagerMaze, room)
        playState.enter(managerMaze)
        playState.execute()

        val argument: ArgumentCaptor<WinState> = ArgumentCaptor.forClass(WinState::class.java)
        verify(managerMaze).changeStateMazeFsm(argument.capture())
        assertTrue(argument.value is WinState)
    }

    @Test
    fun loseInPlayState() {
        val room = Room(objects = listOf(Utensil(name = "Knife")))
        val jsonManagerMaze = mock(JsonManagerMaze::class.java)
        val managerMaze = mock(ManagerMaze::class.java)
        `when`(jsonManagerMaze.arrayRooms).thenReturn(listOf(Room()))

        val playState = PlayState(listOf("Fork"), jsonManagerMaze, room)
        playState.enter(managerMaze)
        playState.execute()

        val argument: ArgumentCaptor<WinState> = ArgumentCaptor.forClass(WinState::class.java)
        verify(managerMaze).changeStateMazeFsm(argument.capture())
        assertTrue(argument.value is LoseState)
    }
}