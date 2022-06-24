package com.edurda77.plugins

import com.edurda77.cache.MemoryCache
import com.edurda77.model.DbModel
import com.edurda77.model.InputData
import com.edurda77.model.InputLoginPassword
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureDataTransferRouting() {


    routing {
        post("/updatedata") {
            val receive = call.receive<InputData>()
            val first = MemoryCache.userList.first { it.login == receive.transferLogin }
            MemoryCache.userList.remove(first)
            MemoryCache.userList.add(
                DbModel(first.login, first.password, first.currentData, receive.transferData)
            )

        }
    }
}