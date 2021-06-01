package com.nicomincuzzi.controller;

public class NotFoundException extends ClientError {
    public NotFoundException(String path) {
        super(404, "Not found: " + path);
    }
}