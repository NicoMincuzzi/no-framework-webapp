package com.nicomincuzzi.frameworkless.maze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Utensil {
    @JsonProperty
    private String name;
}
