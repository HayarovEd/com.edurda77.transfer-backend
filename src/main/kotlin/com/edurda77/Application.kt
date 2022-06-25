package com.edurda77

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.edurda77.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configureLoginRouting()
        configureDataTransferRouting()
        configureRemoveUserRouting()
        configureAddUserRouting()
        configureSerialization()
    }.start(wait = true)
}
