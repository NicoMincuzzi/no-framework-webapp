package com.nicomincuzzi.db;

import java.sql.Connection;

public interface JDBCDriver {
    Connection connect() throws Exception ;
}
