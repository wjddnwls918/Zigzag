package com.example.zigzag.model.list

import android.app.Application
import com.google.gson.GsonBuilder
import java.io.InputStream

class ShopRepository(var application: Application) {

    companion object {
        private var INSTANCE: ShopRepository? = null

        fun getInstance(application: Application): ShopRepository {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(ShopRepository::class) {
                val instance = ShopRepository(application)
                INSTANCE = instance
                return instance
            }
        }
    }

    fun getShopInfoFromJson(): ShopInfo {

        lateinit var info: ShopInfo
        val gson = GsonBuilder().create()

        try {
            val inputStream: InputStream = application.assets.open("shop_list.json")
            val inputString = inputStream.bufferedReader().use { it.readText() }

            info = gson.fromJson(inputString,ShopInfo::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
        }


        return info
    }

}