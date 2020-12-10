package com.example.android_projekt2020

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement

class DatabaseOperations(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        const val DATABASE_NAME="user.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DatabaseInfo.SQL_CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL(DatabaseInfo.SQL_DELETE_TABLE_QUERY)
        onCreate(db!!)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    fun insertData(photo: ByteArray,name: String, password: String, address: String, number: String, email: String){
        val database : SQLiteDatabase = writableDatabase

        val sql: String = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?)"

        val statement: SQLiteStatement = database.compileStatement(sql)
        statement.clearBindings()

        statement.bindBlob(1, photo)
        statement.bindString(2, name)
        statement.bindString(3, password)
        statement.bindString(4, address)
        statement.bindString(5, number)
        statement.bindString(6, email)

        statement.executeInsert()
    }
}