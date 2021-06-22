package com.nicomincuzzi.frameworkless.maze

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Room(
        @JsonProperty
        val id: Int = -1,

        @JsonProperty
        val name: String = "",

        @JsonProperty
        var north: Int? = null,

        @JsonProperty
        var south: Int? = null,

        @JsonProperty
        var east: Int? = null,

        @JsonProperty
        var west: Int? = null,

        @JsonProperty
        val objects: List<Utensil> = listOf()
) {
    fun getRoomById(roomId: Int, rooms: List<Room>): Optional<Room> {
        return rooms.stream().filter { x: Room -> x.id == roomId }.findFirst()
    }
}