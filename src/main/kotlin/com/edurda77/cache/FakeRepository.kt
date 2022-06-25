package com.edurda77.cache

import com.edurda77.model.DbModel

class FakeRepository {
    val userList: MutableList<DbModel> = mutableListOf()

    fun updateData(dbModel: DbModel, currentData: Int) {
        userList.remove(dbModel)
        userList.add(
            DbModel(dbModel.login, dbModel.password, dbModel.currentData, currentData)
        )
    }
    fun removeUser (dbModel: DbModel) {
        userList.remove(dbModel)
    }
    fun addUser (name:String, password: String){
        userList.add(
            DbModel(name, password, 0, 0)
        )
    }
    fun changePassword (dbModel: DbModel, password: String) {
        userList.remove(dbModel)
        DbModel(dbModel.login, password, dbModel.lastData, dbModel.currentData)
    }
    suspend fun getUsers ()=userList
}