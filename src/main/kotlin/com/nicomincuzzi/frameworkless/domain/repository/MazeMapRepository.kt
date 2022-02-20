package com.nicomincuzzi.frameworkless.domain.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.nicomincuzzi.frameworkless.domain.MazeMap
import java.io.IOException


class MazeMapRepository {

    fun retrieve(filename: String?): MazeMap? {
        val input = javaClass.classLoader.getResourceAsStream(filename)
        try {
            return ObjectMapper().readValue(input, MazeMap::class.java)
        } catch (e: IOException) {
            //log.error("Cannot open file.", e)
        }
        return null
    }
}