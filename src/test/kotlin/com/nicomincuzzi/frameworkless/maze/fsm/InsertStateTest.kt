package com.nicomincuzzi.frameworkless.maze.fsm

import com.nicomincuzzi.frameworkless.maze.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import java.util.*

class InsertStateTest {
    @Test
    fun executeInsertState() {
        val managerMaze = mock(ManagerMaze::class.java)

        val scanner = mock(Scanner::class.java)
        `when`(scanner.nextLine()).thenReturn("3").thenReturn("Knife")
        val mazeMap = mock(MazeMap::class.java)
        val jsonManagerMaze = mock(JsonManagerMaze::class.java)
        `when`(jsonManagerMaze.getRoomById(3, mazeMap)).thenReturn(Room())

        val insertState = InsertState(scanner, mazeMap, jsonManagerMaze)
        insertState.enter(managerMaze)
        insertState.execute()

        val argument: ArgumentCaptor<WinState> = ArgumentCaptor.forClass(WinState::class.java)
        verify(managerMaze).changeStateMazeFsm(argument.capture())
        Assertions.assertTrue(argument.value is PlayState)
    }
}