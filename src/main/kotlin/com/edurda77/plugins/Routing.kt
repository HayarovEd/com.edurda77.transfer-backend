package com.edurda77.plugins

import com.edurda77.model.DbModel
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respond("Hallo!")
        }
    }
}
