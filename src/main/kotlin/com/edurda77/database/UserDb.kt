package com.edurda77.database

import com.edurda77.model.DbModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.SimpleDateFormat
import java.util.*

object UserDb : Table("users") {
    private val login = varchar("login", 20)
    private val password = varchar("password", 20)
    private val lastData = integer("lastdata")
    private val currentData = integer("currentdata")
    private val updateDate = varchar("dataofupdate", 15)

    //ver
    fun updateData(dbModel: DbModel, currentData: Int) {
        transaction {
            removeUser(dbModel)
            val tempUser = DbModel(
                login = dbModel.login,
                password = dbModel.password,
                lastData = dbModel.currentData,
                currentData = currentData,
                updateDate = dateToString()
            )
            addUser(tempUser)
        }
    }

    fun removeUser(dbModel: DbModel) {
        transaction {
            UserDb.deleteWhere {
                login like dbModel.login
            }
        }
    }

    //ver
    fun addUser(dbModel: DbModel) {
        transaction {
            UserDb.insert {
                it[login] = dbModel.login
                it[password] = dbModel.password
                it[lastData] = dbModel.lastData
                it[currentData] = dbModel.currentData
                it[updateDate] = dateToString()
            }
        }
    }

    // ver
    fun changePassword(dbModel: DbModel, passwordUser: String) {
        transaction {
            removeUser(dbModel)
            val tempUser = DbModel(
                login = dbModel.login,
                password = passwordUser,
                lastData = dbModel.lastData,
                currentData = dbModel.currentData,
                updateDate = dateToString()
            )
            addUser(tempUser)
        }
    }

    //ver
    fun fetchUser(login: String): DbModel? {
        return try {
            transaction {
                val userModel = UserDb.select { UserDb.login.eq(login) }.single()
                DbModel(
                    login = userModel[UserDb.login],
                    password = userModel[password],
                    lastData = userModel[lastData],
                    currentData = userModel[currentData],
                    updateDate = dateToString()
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    // ver
    fun fetchUsers(): List<DbModel> {
        val users: MutableList<DbModel> = mutableListOf()
        transaction {
            val usersDb = UserDb.selectAll()
            usersDb.forEach {
                users.add(
                    DbModel(
                        login = it[login],
                        password = it[password],
                        lastData = it[lastData],
                        currentData = it[currentData],
                        updateDate = dateToString()
                    )
                )
            }

        }
        return users
    }

    fun correctData(dbModel: DbModel, newLastData: Int, newCurrentData: Int) {
        removeUser(dbModel)
        addUser(
            DbModel(
                login = dbModel.login,
                password = dbModel.password,
                lastData = newLastData,
                currentData = newCurrentData,
                updateDate = dateToString()
            )
        )
    }

    fun dateToString(): String {
        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(date)
    }
}
