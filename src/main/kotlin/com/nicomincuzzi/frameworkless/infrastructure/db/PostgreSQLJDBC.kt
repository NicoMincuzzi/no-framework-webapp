package com.nicomincuzzi.frameworkless.infrastructure.db

import java.lang.Exception
import java.sql.Connection
import java.util.Properties
import java.sql.DriverManager
import java.sql.SQLException

class PostgreSQLJDBC : JDBCDriver {
    @Throws(Exception::class)
    override fun connect(): Connection? {
        val url = "jdbc:postgresql://localhost/test"
        val props = Properties()
        props.setProperty("user", "fred")
        props.setProperty("password", "secret")
        props.setProperty("ssl", "true")
        return try {
            DriverManager.getConnection(url, props)
        } catch (e: SQLException) {
            throw Exception()
        }
    }
}