package com.edurda77.controllers

import com.edurda77.database.UserDb
import com.edurda77.model.AddUserModel
import com.edurda77.model.DbModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AddUserController(private val call: ApplicationCall) {
    suspend fun performAddUser() {
        val receive = call.receive<AddUserModel>()
        val admin = UserDb.fetchUsers().firstOrNull { it.login == receive.login }
        val first = UserDb.fetchUsers().firstOrNull { it.login == receive.user }
        if (receive.login == "Администратор") {
            if (admin != null) {
                if (admin.password == receive.password) {
                    if (first == null) {
                        UserDb.addUser(
                            DbModel(
                                login = receive.user,
                                password = receive.userPassword,
                                lastData = 0,
                                currentData = 0
                            )
                        )
                        call.respond(HttpStatusCode.OK, "Пользователь ${receive.user} добавлен")
                    } else {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Пользователь с таким логином ${receive.user} уже имеется"
                        )
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Пароль неверный")
                }
            }
        } else call.respond(HttpStatusCode.BadRequest, "У Вас нет прав на добавление пользователя")
    }
}