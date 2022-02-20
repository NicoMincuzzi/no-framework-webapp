package com.nicomincuzzi.frameworkless.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Room(
    @JsonProperty val id: Int = 3,
    @JsonProperty val name: String = "Kitchen",
    @JsonProperty var north: Int? = null,
    @JsonProperty var south: Int? = null,
    @JsonProperty var east: Int? = null,
    @JsonProperty var west: Int? = null,
    @JsonProperty val objects: List<Utensil> = listOf()
) {
    fun getRoomById(roomId: Int, rooms: List<Room>): Optional<Room> {
        return rooms.stream().filter { x: Room -> x.id == roomId }.findFirst()
    }

    fun searchIn(findingItems: List<String>): GameResult {
        val foundItems: MutableList<String> = ArrayList()
        for (item in findingItems) {
            for (itemRoom in objects) {
                if (item.equals(itemRoom.name, ignoreCase = true)) {
                    foundItems.add(itemRoom.name)
                }
            }
        }
        if (foundItems.isEmpty())
            foundItems.add("None")

        val gameResult = GameResult( id,name,foundItems)
        return gameResult;
    }
}