package com.nicomincuzzi.frameworkless.maze.fsm

import com.nicomincuzzi.frameworkless.maze.JsonManagerMaze
import com.nicomincuzzi.frameworkless.maze.ManagerMaze
import com.nicomincuzzi.frameworkless.maze.Room
import com.nicomincuzzi.frameworkless.maze.Utensil
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class PlayStateTest {
    @Test
    @Disabled
    fun execute() {
        val utensil = Utensil(name = "Knife")
        val room = Room(objects = listOf(utensil))
        val playState = PlayState(listOf(), mock(JsonManagerMaze::class.java), room)
        val managerMaze = mock(ManagerMaze::class.java)
        playState.enter(managerMaze)
        playState.execute()

        val argument: ArgumentCaptor<WinState> = ArgumentCaptor.forClass(WinState::class.java)
        verify(managerMaze).changeStateMazeFsm(argument.capture())
        assertTrue(argument.value is WinState)
    }
}