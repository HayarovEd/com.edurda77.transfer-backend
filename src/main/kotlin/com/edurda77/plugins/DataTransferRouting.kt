package com.edurda77.plugins

import com.edurda77.controllers.UpdateDataController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDataTransferRouting() {
    routing {
        post("/updatedata") {
            val updateDataController = UpdateDataController(call)
            updateDataController.performUpdateData()
        }
    }
}