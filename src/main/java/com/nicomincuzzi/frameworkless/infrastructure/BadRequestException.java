package com.nicomincuzzi.frameworkless.infrastructure;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class BadRequestException  extends ClientError{
    public BadRequestException(String message) {
        super(SC_BAD_REQUEST, message);
    }
}
