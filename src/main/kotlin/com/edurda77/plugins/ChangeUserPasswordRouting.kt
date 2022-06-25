package com.edurda77.plugins

import com.edurda77.cache.FakeRepository
import com.edurda77.model.ChangeUserPasswordModel
import com.edurda77.model.DbModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureChangePasswordUserRouting() {
    val repository = FakeRepository()
    repository.userList.add(DbModel("Юлия", "Test", 0, 5))
    repository.userList.add(DbModel("Эдуард", "Test", 1, 5))
    repository.userList.add(DbModel("Администратор", "Test", 0, 0))
    routing {
        post("/changePasswordUser") {
            val receive = call.receive<ChangeUserPasswordModel>()
            val first = repository.userList.first { it.login == receive.user }
            val admin = repository.userList.first { it.login == receive.login }
            if (receive.login == "Администратор") {
                if (admin.password == receive.password) {
                    repository.changePassword(first, receive.userPassword)
                    call.respond(HttpStatusCode.OK, "Пароль пользователя ${receive.user} изменен")
                    //call.respond(listOf(repository.userList))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Пароль неверный")
                }
            } else call.respond(HttpStatusCode.BadRequest, "У Вас нет прав на добавление пользователя")

        }
    }
}