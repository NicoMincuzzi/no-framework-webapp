package com.nicomincuzzi.frameworkless.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientError extends RuntimeException {
    private final int status;
    private final String message;
}
