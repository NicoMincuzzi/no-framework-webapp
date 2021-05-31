package com.nicomincuzzi.maze;

import lombok.Data;

import java.util.List;

@Data
public class GameResult {
    private int id;
    private String room;
    private List<String> items;
}
