package com.edurda77.plugins

import com.edurda77.cache.FakeRepository
import com.edurda77.controllers.LoginController
import com.edurda77.model.DbModel
import com.edurda77.model.InputLoginPassword
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    val repository = FakeRepository()
    repository.userList.add(DbModel("Юлия", "Test", 0, 5))
    repository.userList.add(DbModel("Эдуард", "Test", 1, 5))
    repository.userList.add(DbModel("Администратор", "Test", 0, 0))

    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}