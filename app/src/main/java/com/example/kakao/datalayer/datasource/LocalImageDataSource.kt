package com.example.kakao.datalayer.datasource

import android.content.SharedPreferences
import android.util.Log
import com.example.kakao.di.LOCAL_IMAGES
import com.example.kakao.di.LocalModule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class LocalImageDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveImageToLocal(imageUrl: String) {
//        val updatedList = getLocalImages().toMutableList()
        sharedPreferences.edit().putString(LOCAL_IMAGES, imageUrl).apply()
    }

    fun deleteImageToLocal(imageUrl: String) {

    }

    private fun getLocalImages(): List<String> {
        var list = mutableListOf<String>()
        val jsonString = sharedPreferences.getString(LOCAL_IMAGES, "왜안되냐고!!!")
        Log.i("sharePrefTest", "jsonString 1: $jsonString")
        if (jsonString != null) {
            Log.i("sharePrefTest", "jsonString 2: $jsonString")
            list = Gson().fromJson(jsonString, object : TypeToken<List<String>>() {}.type)
        }
        Log.i("sharePrefTest", "jsonString 3: $jsonString")

        return list
    }
}