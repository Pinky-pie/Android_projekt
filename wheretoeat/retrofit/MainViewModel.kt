package com.example.wheretoeat.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONArray

class MainViewModel(): ViewModel() {

    var apiRestaurants: MutableLiveData<MutableList<Restaurant>> = MutableLiveData()

    fun getAllRestaurantsFromDropBox() {
        val client = OkHttpClient()
        val request =
            Request.Builder().url("https://www.dropbox.com/s/94t6su4cimnrdnt/restaurants.json?dl=1")
                .build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("Helo", "onfailure 2 - ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                parseResponse(response.body!!.string())
            }
        })
    }

    fun parseResponse(response: String) {
        val restaurantList = ArrayList<Restaurant>()
        val jsonArray = JSONArray(response)
        val restaurantSize = jsonArray.length()

        (0 until restaurantSize).forEach { index ->
            val jsonObject = jsonArray.getJSONObject(index)
            val apiRestaurant = Restaurant(
                jsonObject.getString("id").toInt(),
                jsonObject.getString("name"),
                jsonObject.getString("address"),
                jsonObject.getString("city"),
                jsonObject.getString("area"),
                jsonObject.getString("postal_code"),
                jsonObject.getString("country"),
                jsonObject.getString("phone"),
                jsonObject.getString("lat").toDouble(),
                jsonObject.getString("lng").toDouble(),
                jsonObject.getString("price").toDouble(),
                jsonObject.getString("reserve_url"),
                jsonObject.getString("mobile_reserve_url"),
                jsonObject.getString("image_url"),
                jsonObject.getString("state"),
            )

            restaurantList.add(apiRestaurant)
        }
        apiRestaurants.postValue(restaurantList)
    }

}