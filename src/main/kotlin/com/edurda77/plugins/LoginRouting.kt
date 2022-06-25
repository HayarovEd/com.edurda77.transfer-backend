package com.edurda77.plugins

import com.edurda77.cache.FakeRepository
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
            val receive = call.receive<InputLoginPassword>()
            val first = repository.userList.firstOrNull { it.login == receive.login }
            if (first == null) {
                call.respond(HttpStatusCode.BadRequest, "Пользователь не найден")
            } else
                if (first.password == receive.password) {
                    when (first.login) {
                        "Администратор" -> {
                            call.respond(listOf(repository.userList))
                        }
                        else -> {
                            call.respond(
                                DbModel(
                                    login = first.login,
                                    password = first.password,
                                    lastData = first.lastData,
                                    currentData = first.currentData
                                )
                            )}
                    }

                } else {
                    call.respond(HttpStatusCode.BadRequest, "пароль неверный")
                }

        }
    }
}