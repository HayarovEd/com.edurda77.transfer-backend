package com.edurda77.cache

import com.edurda77.model.DbModel

class FakeRepository {
    val userList: MutableList<DbModel> = mutableListOf()

    fun updateData(dbModel: DbModel, currentData: Int) {
        userList.remove(dbModel)
        userList.add(
            DbModel(
                login = dbModel.login,
                password = dbModel.password,
                lastData = dbModel.currentData,
                currentData = currentData
            )
        )
    }

    fun removeUser(dbModel: DbModel) {
        userList.remove(dbModel)
    }

    fun addUser(name: String, password: String) {
        userList.add(
            DbModel(
                login = name,
                password = password,
                lastData = 0,
                currentData = 0)
        )
    }

    fun changePassword(dbModel: DbModel, password: String) {
        userList.remove(dbModel)
        userList.add(DbModel(
            login = dbModel.login,
            password = password,
            lastData = dbModel.lastData,
            currentData = dbModel.currentData)
        )
    }
    //fun getUsers ()=userList
}