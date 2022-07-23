package com.edurda77.controllers

import com.edurda77.database.UserDb
import com.edurda77.model.AddUserModel
import com.edurda77.model.CorrectDataModel
import com.edurda77.model.DbModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class CorrectDataController(private val call: ApplicationCall) {
    suspend fun performCorrectData() {
        val receive = call.receive<CorrectDataModel>()
        val admin = UserDb.fetchUsers().firstOrNull { it.login == receive.login }
        val first = UserDb.fetchUsers().firstOrNull { it.login == receive.user }
        if (receive.login == "Администратор") {
            if (admin != null) {
                if (admin.password == receive.password) {
                    if (first != null) {
                        UserDb.correctData(
                            first, receive.lastData, receive.currentData
                        )
                        call.respond(HttpStatusCode.OK, "Данные для  ${receive.user} исправлены")
                    } else {
                        call.respond(
                            call.respond(HttpStatusCode.BadRequest, "Пользователя ${receive.user} нет")
                        )
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Пароль неверный")
                }
            }
        } else call.respond(HttpStatusCode.BadRequest, "У Вас нет прав на добавление пользователя")
    }
}