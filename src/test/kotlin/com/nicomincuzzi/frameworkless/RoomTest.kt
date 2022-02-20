package com.nicomincuzzi.frameworkless

import com.nicomincuzzi.frameworkless.domain.GameResult
import com.nicomincuzzi.frameworkless.domain.Room
import com.nicomincuzzi.frameworkless.domain.Utensil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RoomTest {
    @Test
    fun searchInDiningRoomTheKnife() {
        val room = Room(id = 2, name = "Dining Room", south = 1, west = 3, east = 4, objects = listOf())
        val gameResult: GameResult = room.searchIn(listOf("Knife"))
        Assertions.assertEquals(1, gameResult.items.size)
        Assertions.assertEquals("None", gameResult.items.first())
    }

    @Test
    fun searchInKitchenTheKnife() {
        val room = Room(id = 3, name = "Kitchen", east = 2, objects = listOf(Utensil(name = "Knife")))
        val gameResult: GameResult = room.searchIn(listOf("Knife"))
        Assertions.assertEquals(1, gameResult.items.size)
        Assertions.assertEquals("Knife", gameResult.items.first())
    }

    @Test
    fun searchInSunRoomThePottedPlant() {
        val room = Room(id = 4, name = "Sun Room", west = 2, objects = listOf(Utensil(name = "Potted Plant")))
        val gameResult: GameResult = room.searchIn(listOf("Knife"))
        Assertions.assertEquals(1, gameResult.items.size)
        Assertions.assertEquals("None", gameResult.items.first())
    }
}