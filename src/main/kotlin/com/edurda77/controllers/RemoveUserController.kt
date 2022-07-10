package com.edurda77.controllers

import com.edurda77.database.UserDb
import com.edurda77.model.ChangeUserPasswordModel
import com.edurda77.model.RemoveUserModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class RemoveUserController(private val call: ApplicationCall) {
    suspend fun performRemoveUser() {
        val receive = call.receive<RemoveUserModel>()
        val first = UserDb.fetchUsers().firstOrNull { it.login == receive.user }
        val admin = UserDb.fetchUsers().firstOrNull { it.login == receive.login }
        if (receive.login == "Администратор") {
            if (admin != null) {
                if (admin.password == receive.password) {
                    if (first != null) {
                        UserDb.removeUser(first)
                    }
                    if (first != null) {
                        call.respond(HttpStatusCode.OK, "Пользователь ${first.login} удален")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Пароль неверный")
                }
            }
        } else call.respond(HttpStatusCode.BadRequest, "У Вас нет прав на удаление пользователя")
    }
}