package com.example.wheretoeat.databases.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }

    fun readData(): LiveData<User>{
        return repository.readData()
    }

    fun updateData(name: String, address: String, email: String, phone: String, image: ByteArray){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(name, address, email, phone, image)
        }
    }
}