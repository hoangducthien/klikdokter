package com.example.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class FileCache(context: Context) {

    var cache: SharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    val gson: Gson = Gson()

    fun <T> update(key: String, value: T) {
        when (value) {
            is Long -> cache.edit().putLong(key, value).apply()
            is Int -> cache.edit().putInt(key, value).apply()
            is String -> cache.edit().putString(key, value).apply()
            is Float -> cache.edit().putFloat(key, value).apply()
            is Boolean -> cache.edit().putBoolean(key, value).apply()
            else -> {
                cache.edit().putString(key, gson.toJson(value)).apply()
            }
        }
    }

    inline fun <reified T> get(key: String): T? {
        val cacheData = cache.getString(key, "")
        return if (cacheData.isNullOrBlank()){
            null
        } else {
            gson.fromJson(cacheData, T::class.java)
        }
    }

}
