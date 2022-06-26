package com.edurda77.database

import com.edurda77.model.DbModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UserDb : Table ("users") {
    private val login  = varchar("login", 20)
    private val password = varchar("password", 20)
    private val lastData = integer("lastdata")
    private val currentData = integer("currentdata")

    fun updateData (dbModel: DbModel, currentData: Int) {
        transaction {
            UserDb.update {
                it [login] = dbModel.login
                it [password] = dbModel.password
                it [lastData] = dbModel.currentData
                it [UserDb.currentData] = currentData
            }
        }
    }
    fun removeUser(dbModel: DbModel) {
        transaction {
            UserDb.deleteWhere {
                login like dbModel.login
            }
        }
    }
    fun addUser(dbModel: DbModel) {
        transaction {
            UserDb.insert {
                it [login] = dbModel.login
                it [password] = dbModel.password
                it [lastData] = dbModel.lastData
                it [currentData] = dbModel.currentData
            }
        }
    }
    fun changePassword(dbModel: DbModel, password: String) {
        transaction {
            UserDb.insert {
                it [login] = dbModel.login
                it [UserDb.password] = password
                it [lastData] = dbModel.lastData
                it [currentData] = dbModel.currentData
            }
        }
    }
    fun fetchUser(login: String): DbModel? {
        return try {
            transaction {
                val userModel = UserDb.select { UserDb.login.eq(login) }.single()
                DbModel(
                    login = userModel[UserDb.login],
                    password = userModel[password],
                    lastData = userModel[lastData],
                    currentData = userModel[currentData]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
    fun fetchUsers(login: String): List<DbModel>? {
        return try {
            transaction {
                val userList: MutableList<DbModel> = mutableListOf()
                val users = UserDb.selectAll()

                )
            }
        } catch (e: Exception) {
            null
        }
    }

}
