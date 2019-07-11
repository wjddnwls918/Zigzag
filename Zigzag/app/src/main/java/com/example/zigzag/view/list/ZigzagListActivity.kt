package com.example.zigzag.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.LinearLayout.HORIZONTAL
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zigzag.R
import com.example.zigzag.common.BaseInfo
import com.example.zigzag.databinding.ActivityZiagzagListBinding
import com.example.zigzag.model.list.Shop
import com.example.zigzag.model.list.ShopInfo
import com.example.zigzag.view.filter.FilterActivity
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class ZigzagListActivity : AppCompatActivity(), ZigzagListNavigator {

    lateinit var binding: ActivityZiagzagListBinding
    lateinit var viewModel: ZigzagListViewModel
    lateinit var adapter: ZigzagListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ziagzag_list)

        viewModel = ViewModelProviders.of(this).get(ZigzagListViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.navigator = this

        setDataSet()

        initRecyclerView()

        initObserve()

    }

    fun initRecyclerView() {

        val lm = LinearLayoutManager(this)
        binding.rcvZigzagList.layoutManager = lm

        binding.rcvZigzagList.adapter = adapter

        val itemDecorator = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        binding.rcvZigzagList.addItemDecoration(itemDecorator)
    }


    fun initObserve() {
        viewModel.observeAgeFilter.observe(this, androidx.lifecycle.Observer {

            it.run {
                if (viewModel.observeAgeFilter.value == 0 && viewModel.observeStyleFilter.value == 0) {
                    reduceMapView()
                } else {
                    extendMapView()

                    setFilterText()
                }

            }

        })
    }

    fun setFilterText() {
        var filterViewText = ""

        if (viewModel.ageFilter != 0) {
            //연령대 필터 텍스트
            for (i in 0..6) {
                if (viewModel.ageFilter and (1 shl i) == 1 shl i) {
                    filterViewText += BaseInfo.ageType.get(i)
                    if (i != 6)
                        filterViewText += ","
                }
            }

            if (filterViewText[(filterViewText.length - 1)] == ',')
                filterViewText = filterViewText.substring(0, filterViewText.length - 1)
        }

        if (viewModel.styleFilter != 0) {

            if (viewModel.ageFilter != 0)
                filterViewText += '/'

            //스타일 필터 텍스트
            for (i in 0..13) {
                if ((viewModel.styleFilter and (1 shl i)) == 1 shl i) {
                    filterViewText += BaseInfo.styleTypeNumToString.get(i)
                    if (i != 13)
                        filterViewText += ","
                }
            }

            if (filterViewText.get(filterViewText.length - 1) == ',')
                filterViewText = filterViewText.substring(0, filterViewText.length - 1)
        }

        viewModel.filterViewText.set(filterViewText)
    }

    fun extendMapView() {
        binding.glEndOfFilterText.setGuidelinePercent(0.12.toFloat())
    }

    fun reduceMapView() {
        binding.glEndOfFilterText.setGuidelinePercent(0.07.toFloat())
    }

    fun setDataSet() {
        adapter = ZigzagListAdapter(viewModel.getShopListFromJson())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == 101) {
            viewModel.ageFilter = data?.getStringExtra("ageFilter")?.toInt() ?: 0
            viewModel.styleFilter = data?.getStringExtra("styleFilter")?.toInt() ?: 0

            viewModel.observeAgeFilter.value = viewModel.ageFilter
            viewModel.observeStyleFilter.value = viewModel.styleFilter

            if (viewModel.ageFilter > 0 && viewModel.styleFilter > 0) {
                //두 개 필터 모두 선택
                adapter.setItem(viewModel.getListByAgeAndStyle())
            } else if (viewModel.ageFilter > 0) {
                adapter.setItem(viewModel.getListByAgeFilter())
            } else if (viewModel.styleFilter > 0) {
                adapter.setItem(viewModel.getListByStyleFilter())
            } else {
                //필터 없음
                adapter.setItem(viewModel.shopInfo.get()?.list ?: ArrayList())
            }


        }
    }

    override fun onFilterClick() {
        val intent = Intent(this, FilterActivity::class.java)
        startActivityForResult(intent, 100)
    }

}
