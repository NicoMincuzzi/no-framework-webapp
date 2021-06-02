package com.nicomincuzzi.frameworkless.maze;

import com.nicomincuzzi.frameworkless.maze.fsm.MazeState;
import com.nicomincuzzi.frameworkless.maze.fsm.StartState;
import com.nicomincuzzi.frameworkless.maze.fsm.MazeFsm;

public class ManagerMaze {
	
	private final MazeFsm<ManagerMaze> mazeFsm = new MazeFsm<>();

	public void runMazeRetroRoutePuzzle() {
		mazeFsm.startMazeRoutePuzzle(this, new StartState());
	}

	public void changeStateMazeFsm(MazeState<ManagerMaze> state) {
		mazeFsm.changeState(state);
	}
}
