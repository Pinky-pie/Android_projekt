package com.example.wheretoeat.databases.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

    fun readData(): LiveData<User> {
        return userDao.readData()
    }

    fun updateData(name: String, address: String, email: String, phone: String, image: ByteArray){
        userDao.updateData(name,address, email, phone, image)
    }
}
