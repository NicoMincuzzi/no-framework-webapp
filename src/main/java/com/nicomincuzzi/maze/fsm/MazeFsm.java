package com.nicomincuzzi.maze.fsm;

public class MazeFsm <T>{
	
	private MazeState<T> currentState;
	private MazeState<T> previousState;
	
	//Represent the Finite State Machine
	private T owner; 

	public void startMazeRoutePuzzle(T o, MazeState<T> initialState) {
		owner = o;
		changeState(initialState);
	}

	public void changeState(MazeState<T> newState) {
		previousState = currentState;
		
		if(previousState != null)
			previousState.exit();

		currentState = newState;
		
		currentState.enter(owner);
		
		//Update the current state of the FSM. 
		if (currentState != null) 
			currentState.execute();
	}

	public void  revertToPreviousState() {
		if (previousState != null)
		  changeState(previousState);
	}
}
