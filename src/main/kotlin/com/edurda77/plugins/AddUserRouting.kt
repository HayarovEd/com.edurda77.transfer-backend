package com.edurda77.plugins

import com.edurda77.controllers.AddUserController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAddUserRouting() {

    routing {
        post("/addUser") {
            val addUserController = AddUserController(call)
            addUserController.performAddUser()
        }
    }
}