package com.example.zigzag.view.list

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.zigzag.model.list.Shop
import com.example.zigzag.model.list.ShopInfo
import com.example.zigzag.model.list.ShopRepository

class ZigzagListViewModel(application: Application) : AndroidViewModel(application) {

    val repository:ShopRepository
    var shopInfo: ObservableField<ShopInfo> = ObservableField()

    lateinit var navigator: ZigzagListNavigator

    init {
        repository = ShopRepository.getInstance(application)
    }

    fun getShopListFromJson() :MutableList<Shop> {

        val result: ShopInfo = repository.getShopInfoFromJson()
        shopInfo.set(result)

        result.list.sortWith(compareByDescending ({it.`0`}))

        Log.d("datacheck", result.week)
        Log.d("datacheck", result.list.get(0).`0`.toString())
        Log.d("datacheck", result.list.get(1).`0`.toString())
        Log.d("datacheck", result.list.get(2).`0`.toString())
        Log.d("datacheck", result.list.get(3).`0`.toString())


        return result.list
    }

    fun onFilterClick() {
        navigator.onFilterClick()
    }

}