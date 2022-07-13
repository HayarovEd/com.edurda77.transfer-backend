package com.edurda77

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.edurda77.plugins.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun main() {
    val config = HikariConfig("hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)
    /*Database.connect("jdbc:postgresql://localhost:5432/Parkovy50", "org.postgresql.Driver",
    "postgres", "Khayarov1977!")*/
    //embeddedServer(Netty, port = 8080, host = "127.0.0.1")
    embeddedServer(Netty, port = System.getenv("PORT").toInt()){
        configureRouting()
        configureLoginRouting()
        configureDataTransferRouting()
        configureRemoveUserRouting()
        configureAddUserRouting()
        configureChangePasswordUserRouting()
        configureSerialization()
    }.start(wait = true)
}
