package com.nicomincuzzi.maze.fsm;

public interface MazeState<T> {
    void enter(T maze);

    void execute();

    default void exit() {
    }
}
