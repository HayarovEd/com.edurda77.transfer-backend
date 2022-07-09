package com.edurda77.plugins

import com.edurda77.controllers.RemoveUserController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRemoveUserRouting() {

    routing {
        post("/removeUser") {
           val removeUserController = RemoveUserController(call)
            removeUserController.performRemoveUser()
        }
    }
}
