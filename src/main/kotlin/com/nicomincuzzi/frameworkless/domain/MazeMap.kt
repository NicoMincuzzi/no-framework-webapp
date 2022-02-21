package com.nicomincuzzi.frameworkless.domain

import com.fasterxml.jackson.annotation.JsonProperty

class MazeMap {
    @JsonProperty val rooms: List<Room> = listOf()

    fun getRoomById(roomId: Int): Room? {
        for (room in rooms) {
            if (room.id == roomId) return room
        }
        return null
    }

    fun getArrayRooms(): List<Room> {
        return rooms
    }
}