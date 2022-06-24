package com.edurda77.cache

import com.edurda77.model.DbModel

object MemoryCache {
    val userList: MutableList<DbModel> = mutableListOf()
    fun updateData(dbModel: DbModel, currentData: Int) {
        userList.remove(dbModel)
        userList.add(
            DbModel(dbModel.login, dbModel.password, dbModel.currentData, currentData)
        )
    }
}

