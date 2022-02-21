package com.nicomincuzzi.frameworkless.application

import com.nicomincuzzi.frameworkless.domain.JsonManagerMaze
import com.nicomincuzzi.frameworkless.domain.MazeMap
import com.nicomincuzzi.frameworkless.domain.Room
import com.nicomincuzzi.frameworkless.domain.repository.MazeMapRepository

class CreateGame {
    fun create(roomNumber: String): Room? {
        val mazeMap = MazeMapRepository().retrieve("map.json")
        val jsonMngMaze = JsonManagerMaze()
        return retrieveRoomMaze(roomNumber.toInt(), mazeMap, jsonMngMaze)
    }

    fun retrieveRoomMaze(roomNumber: Int, mazeMap: MazeMap?, jsonMngMaze: JsonManagerMaze): Room? {
        val roomMaze = jsonMngMaze.getRoomById(roomNumber, mazeMap)
        if (roomMaze == null) {
            //log.warn("Please insert a valid room number!")
            return null
        }
        return roomMaze
    }
}