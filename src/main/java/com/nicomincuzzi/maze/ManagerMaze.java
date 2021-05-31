package com.nicomincuzzi.maze;

import com.nicomincuzzi.maze.fsm.MazeFsm;
import com.nicomincuzzi.maze.fsm.MazeState;
import com.nicomincuzzi.maze.fsm.StartState;

public class ManagerMaze {
	
	private final MazeFsm<ManagerMaze> mazeFsm = new MazeFsm<>();

	public void runMazeRetroRoutePuzzle() {
		mazeFsm.startMazeRoutePuzzle(this, new StartState());
	}

	public void changeStateMazeFsm(MazeState<ManagerMaze> state) {
		mazeFsm.changeState(state);
	}
}
