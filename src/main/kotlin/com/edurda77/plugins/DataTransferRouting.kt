package com.edurda77.plugins

import com.edurda77.cache.FakeRepository
import com.edurda77.model.DbModel
import com.edurda77.model.InputData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureDataTransferRouting() {
    val repository = FakeRepository()
    repository.userList.add(DbModel("Юлия", "Test", 0, 5))
    routing {
        post("/updatedata") {
            val receive = call.receive<InputData>()
            val first = repository.userList.first { it.login == receive.transferLogin }
            //fakeRepository.userList.remove(first)
            if (first.password == receive.transferPassword) {
                repository.updateData(first, receive.transferData)
                call.respond(HttpStatusCode.OK, "Показания переданы")
            } else {
                call.respond(HttpStatusCode.BadRequest, "пароль неверный")
            }

            /*fakeRepository.userList.add(
                DbModel(first.login, first.password, first.currentData, receive.transferData)
            )*/

        }
    }
}