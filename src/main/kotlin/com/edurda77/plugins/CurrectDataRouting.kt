package com.edurda77.plugins

import com.edurda77.controllers.AddUserController
import com.edurda77.controllers.CorrectDataController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCorrectDataRouting() {

    routing {
        post("/correctData") {
            val correctDataController = CorrectDataController(call)
            correctDataController.performCorrectData()
        }
    }
}