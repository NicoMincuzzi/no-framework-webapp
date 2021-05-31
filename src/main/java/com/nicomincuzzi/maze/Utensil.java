package com.nicomincuzzi.maze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Utensil {
    @JsonProperty
    private String name;
}
