
package com.example.android_projekt2020.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants ORDER BY id ASC")
    fun readAllData(): LiveData<List<Restaurant>>
}