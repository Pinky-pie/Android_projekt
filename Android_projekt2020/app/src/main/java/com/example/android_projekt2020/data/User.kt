package com.example.android_projekt2020.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        var name: String = "",
        var address: String = "",
        var phone: String = "",
        var email: String = ""
//        var image: ByteArray? = null
)