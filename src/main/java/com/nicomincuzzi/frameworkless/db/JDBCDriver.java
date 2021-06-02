package com.nicomincuzzi.frameworkless.db;

import java.sql.Connection;

public interface JDBCDriver {
    Connection connect() throws Exception ;
}
