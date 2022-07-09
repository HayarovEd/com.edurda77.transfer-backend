package com.edurda77.controllers

import com.edurda77.database.UserDb
import com.edurda77.model.InputData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UpdateDataController(private val call: ApplicationCall) {
    suspend fun performUpdateData () {
        val receive = call.receive<InputData>()
        val first = UserDb.fetchUsers().firstOrNull { it.login == receive.transferLogin }
        if (first != null) {
            if (first.password == receive.transferPassword) {
                UserDb.updateData(first, receive.transferData)
                call.respond(HttpStatusCode.OK, "Показания переданы")
            } else {
                call.respond(HttpStatusCode.BadRequest, "пароль неверный")
            }
        }
    }
}