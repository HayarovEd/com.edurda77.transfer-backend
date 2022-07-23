package com.edurda77.plugins

import com.edurda77.controllers.LoginController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {


    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}