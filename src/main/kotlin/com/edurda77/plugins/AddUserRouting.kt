package com.edurda77.plugins

import com.edurda77.cache.FakeRepository
import com.edurda77.controllers.AddUserController
import com.edurda77.model.AddUserModel
import com.edurda77.model.DbModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureAddUserRouting() {

    routing {
        post("/addUser") {
            val addUserController = AddUserController(call)
            addUserController.performAddUser()
        }
    }
}