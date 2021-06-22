package com.nicomincuzzi.frameworkless.maze

import com.fasterxml.jackson.annotation.JsonProperty

data class Utensil(
        @JsonProperty
        val name: String = ""
)