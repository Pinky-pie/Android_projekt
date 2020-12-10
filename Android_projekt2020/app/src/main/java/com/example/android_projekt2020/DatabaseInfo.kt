package com.example.android_projekt2020

import android.provider.BaseColumns

object DatabaseInfo {

    const val SQL_CREATE_TABLE_QUERY =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.COLUMN_OWNER_PHOTO} TEXT," +
                "${TableInfo.COLUMN_OWNER_NAME} TEXT," +
                "${TableInfo.COLUMN_OWNER_PASSWORD} TEXT," +
                "${TableInfo.COLUMN_OWNER_ADDRESS} TEXT," +
                "${TableInfo.COLUMN_OWNER_NUMBER} TEXT," +
                "${TableInfo.COLUMN_OWNER_EMAIL} TEXT," +
                "${TableInfo.COLUMN_OWNER_FAVOURITES} TEXT)"

    const val SQL_DELETE_TABLE_QUERY="DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"

    object TableInfo : BaseColumns{
        const val TABLE_NAME = "user"
        const val COLUMN_OWNER_PHOTO = "ownerPhoto"
        const val COLUMN_OWNER_NAME = "ownerName"
        const val COLUMN_OWNER_PASSWORD = "ownerPassword"
        const val COLUMN_OWNER_ADDRESS = "ownerAddress"
        const val COLUMN_OWNER_NUMBER = "ownerPhone_number"
        const val COLUMN_OWNER_EMAIL = "ownerEmail"
        const val COLUMN_OWNER_FAVOURITES = "ownerFavourites"

    }
}

//Prof photo, Name, Address, Number, Email, Favourites