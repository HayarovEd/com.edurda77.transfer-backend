package com.edurda77

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.edurda77.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.1.105") {
        configureRouting()
        configureLoginRouting()
        configureSerialization()
    }.start(wait = true)
}
