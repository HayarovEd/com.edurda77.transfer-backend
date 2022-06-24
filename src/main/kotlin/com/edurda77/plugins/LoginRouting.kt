package com.edurda77.plugins

import com.edurda77.cache.MemoryCache
import com.edurda77.cache.MemoryCache.userList
import com.edurda77.model.DbModel
import com.edurda77.model.InputLoginPassword
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    userList.add(DbModel("Юлия", "Test", 0, 5))

    routing {
        post("/login") {
            val receive = call.receive<InputLoginPassword>()
            val first = MemoryCache.userList.firstOrNull { it.login == receive.inputLogin }
            if (first == null) {
                call.respond(HttpStatusCode.BadRequest, "Пользователь не найден")
            } else
                if (first.password == receive.inputPassword) {
                    call.respond(
                        DbModel(
                            login = first.login,
                            password = first.password,
                            lastData = first.lastData,
                            currentData = first.currentData
                        )
                    )
                } else {
                    call.respond(HttpStatusCode.BadRequest, "пароль неверный")
                }
            /*if (MemoryCache.userList.map { it.login }.contains(receive.inputLogin)){
                if (MemoryCache.userList.map { it.password }.contains(receive.inputPassword)) {

                    println("Залогинились")
                    call.respond (DbModel())
                } else {
                    println("пароль неверный")
                    call.respond (HttpStatusCode.Conflict, "пароль неверный")
                }
            } else {
                println("Такого пользователя нет")
                call.respond (HttpStatusCode.Conflict, "Такого пользователя нет")
            }*/
            //call.respond(HttpStatusCode.BadRequest)
        }
    }
}