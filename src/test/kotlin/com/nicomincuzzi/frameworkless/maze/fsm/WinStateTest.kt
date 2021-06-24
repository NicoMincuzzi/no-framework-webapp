package com.nicomincuzzi.frameworkless.maze.fsm

import com.nicomincuzzi.frameworkless.maze.ManagerMaze
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

internal class WinStateTest {
    @Test
    fun executeWinState() {
        val managerMaze = mock(ManagerMaze::class.java)

        val winState = WinState()
        winState.enter(managerMaze)
        winState.execute()

        val argument: ArgumentCaptor<WinState> = ArgumentCaptor.forClass(WinState::class.java)
        verify(managerMaze).changeStateMazeFsm(argument.capture())
        assertTrue(argument.value is LeaveState)
    }
}