package com.nicomincuzzi.frameworkless.maze.fsm

import com.nicomincuzzi.frameworkless.maze.ManagerMaze
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

internal class LoseStateTest {
    @Test
    fun executeLoseState() {
        val managerMaze = Mockito.mock(ManagerMaze::class.java)

        val loseState = LoseState()
        loseState.enter(managerMaze)
        loseState.execute()

        val argument: ArgumentCaptor<WinState> = ArgumentCaptor.forClass(WinState::class.java)
        Mockito.verify(managerMaze).changeStateMazeFsm(argument.capture())
        Assertions.assertTrue(argument.value is LeaveState)
    }
}