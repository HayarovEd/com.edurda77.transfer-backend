package com.edurda77

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.edurda77.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/Parkovy50", "org.postgresql.Driver",
    "postgres", "Khayarov1977!")
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configureLoginRouting()
        configureDataTransferRouting()
        configureRemoveUserRouting()
        configureAddUserRouting()
        configureChangePasswordUserRouting()
        configureSerialization()
    }.start(wait = true)
}
