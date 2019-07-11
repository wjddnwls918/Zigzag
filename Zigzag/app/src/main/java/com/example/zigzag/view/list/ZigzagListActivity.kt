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
import com.example.zigzag.databinding.ActivityZiagzagListBinding
import com.example.zigzag.model.list.Shop
import com.example.zigzag.model.list.ShopInfo
import com.example.zigzag.view.filter.FilterActivity
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class ZigzagListActivity : AppCompatActivity(),ZigzagListNavigator {

    lateinit var binding: ActivityZiagzagListBinding
    lateinit var viewModel: ZigzagListViewModel
    lateinit var adapter:ZigzagListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ziagzag_list)

        viewModel = ViewModelProviders.of(this).get(ZigzagListViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.navigator = this

        setDataSet()

        initRecyclerView()

    }

    fun initRecyclerView() {

        val lm = LinearLayoutManager(this)
        binding.rcvZigzagList.layoutManager = lm

        binding.rcvZigzagList.adapter = adapter

        val itemDecorator = DividerItemDecoration(this,LinearLayoutManager(this).orientation)
        binding.rcvZigzagList.addItemDecoration(itemDecorator)
    }


    fun setDataSet() {
        adapter = ZigzagListAdapter(viewModel.getShopListFromJson())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onFilterClick() {
        val intent = Intent(this,FilterActivity::class.java)
        startActivityForResult(intent,100)
    }

}
