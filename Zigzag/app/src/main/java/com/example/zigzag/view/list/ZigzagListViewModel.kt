package com.example.zigzag.view.list

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.zigzag.common.BaseInfo
import com.example.zigzag.model.list.Shop
import com.example.zigzag.model.list.ShopInfo
import com.example.zigzag.model.list.ShopRepository
import java.util.*
import kotlin.collections.ArrayList

class ZigzagListViewModel(application: Application) : AndroidViewModel(application) {

    val repository: ShopRepository
    var shopInfo: ObservableField<ShopInfo> = ObservableField()

    lateinit var navigator: ZigzagListNavigator

    var ageFilter = 0
    var styleFilter = 0

    var observeAgeFilter = MutableLiveData(0)
    var observeStyleFilter = MutableLiveData(0)

    var filterViewText = ObservableField<String>()

    init {
        repository = ShopRepository.getInstance(application)
    }

    fun getShopListFromJson(): MutableList<Shop> {

        val result: ShopInfo = repository.getShopInfoFromJson()
        shopInfo.set(result)

        result.list.sortWith(compareByDescending({ it.`0` }))

        return result.list
    }

    fun onFilterClick() {
        navigator.onFilterClick()
    }

    fun getListByAgeAndStyle(): MutableList<Shop> {
        val tempList = ArrayList<Shop>()

        for (shop in shopInfo.get()?.list ?: ArrayList()) {


            var isOk = checkAge(shop.A)
            if (!isOk) {
                continue
            }

            isOk = checkStyle(shop.S)

            if (!isOk)
                continue
            else {
                tempList.add(shop)
            }
        }

        sortList(tempList)

        return tempList
    }

    fun getListByAgeFilter(): MutableList<Shop> {
        val tempList = ArrayList<Shop>()

        for (shop in shopInfo.get()?.list ?: ArrayList()) {

            var isOk = checkAge(shop.A)

            if (!isOk) {
                continue
            } else {
                tempList.add(shop)
            }
        }
        return tempList
    }

    fun getListByStyleFilter(): MutableList<Shop> {
        val tempList = ArrayList<Shop>()

        for (shop in shopInfo.get()?.list ?: ArrayList()) {

            val isOk = checkStyle(shop.S)

            if (!isOk)
                continue
            else {
                tempList.add(shop)
            }
        }

        sortList(tempList)

        return tempList
    }

    fun checkAge(tempAge: Array<Int>): Boolean {
        var isOk = false
        //연령대 체크
        for ((index, value) in tempAge.withIndex()) {
            if (value == 1 && ((ageFilter and (1 shl index)) == 1 shl index)) {
                isOk = true
                break
            }
        }
        return isOk
    }

    fun checkStyle(tempStyle: String): Boolean {
        //스타일 체크
        var isOk = false
        val transStyle = tempStyle.split(",")
        for (style in transStyle) {
            val tempPosition = BaseInfo.styleTypeStringToNum[style] ?: -1
            if (tempPosition != -1 && ((styleFilter and (1 shl tempPosition)) == 1 shl tempPosition)) {
                isOk = true
                break
            }
        }

        return isOk
    }

    fun sortList(list: MutableList<Shop>) {

        list.sortedWith(compareByDescending <Shop> {
            styleFitlerBitCount(it.S)
        }.thenByDescending { it.`0` })

    }

    fun styleFitlerBitCount(S: String): Int {
        val styleSplit = S.split(",")
        var count = 0

        for (item in styleSplit) {
            val position = BaseInfo.styleTypeStringToNum[item] ?: -1
            if (styleFilter and (1 shl position) == 1 shl position)
                count += 1
        }

        return count
    }
}
