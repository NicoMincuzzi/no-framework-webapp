package com.nicomincuzzi.frameworkless.infrastructure.db;

import java.sql.Connection;

public interface JDBCDriver {
    Connection connect() throws Exception ;
}
