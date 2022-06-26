package com.edurda77.controllers

import com.edurda77.database.UserDb
import com.edurda77.model.DbModel
import com.edurda77.model.InputLoginPassword
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin() {
        val receive = call.receive<InputLoginPassword>()
        val userDb = UserDb.fetchUser(receive.login)
        val usersDb = UserDb.fetchUsers()
        if (userDb == null) {
            call.respond(HttpStatusCode.BadRequest, "Пользователь не найден")
        } else
            if (userDb.password == receive.password) {
                when (userDb.login) {
                    "Администратор" -> {
                        call.respond(listOf(usersDb))
                    }
                    else -> {
                        call.respond(
                            DbModel(
                                login = userDb.login,
                                password = userDb.password,
                                lastData = userDb.lastData,
                                currentData = userDb.currentData
                            )
                        )}
                }

            } else {
                call.respond(HttpStatusCode.BadRequest, "пароль неверный")
            }
    }
}