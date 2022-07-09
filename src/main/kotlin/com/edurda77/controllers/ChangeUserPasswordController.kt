package com.edurda77.controllers

import com.edurda77.database.UserDb
import com.edurda77.model.ChangeUserPasswordModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ChangeUserPasswordController(private val call: ApplicationCall) {
    suspend fun performChangeUserPassword () {
        val receive = call.receive<ChangeUserPasswordModel>()
        val admin = UserDb.fetchUsers().firstOrNull { it.login == receive.login }
        val first = UserDb.fetchUsers().firstOrNull { it.login == receive.user }
        if (receive.login == "Администратор") {
            if (admin != null) {
                if (admin.password == receive.password) {
                    if (first != null) {
                        UserDb.changePassword(first, receive.userPassword)
                    }
                    call.respond(HttpStatusCode.OK, "Пароль пользователя ${receive.user} изменен")
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Пароль неверный")
                }
            }
        } else call.respond(HttpStatusCode.BadRequest, "У Вас нет прав на добавление пользователя")
    }
}