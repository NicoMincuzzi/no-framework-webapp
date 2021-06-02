package com.nicomincuzzi.frameworkless.maze.fsm;

public interface MazeState<T> {
    void enter(T maze);

    void execute();

    default void exit() {
    }
}
