package com.nicomincuzzi.frameworkless.infrastructure.db

import java.lang.Exception
import java.sql.Connection

interface JDBCDriver {
    @Throws(Exception::class)
    fun connect(): Connection?
}