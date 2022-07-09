package com.edurda77.plugins

import com.edurda77.controllers.ChangeUserPasswordController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureChangePasswordUserRouting() {

    routing {
        post("/changePasswordUser") {
            val changeUserPasswordController = ChangeUserPasswordController(call)
            changeUserPasswordController.performChangeUserPassword()
        }
    }
}