package com.nicomincuzzi.frameworkless.domain

data class GameResult (
    val id: Int = 0,
    val room: String = "",
    val items: List<String> = listOf()
)