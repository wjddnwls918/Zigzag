package com.example.zigzag.view.filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.zigzag.R
import com.example.zigzag.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity(), FilterNavigator {

    lateinit var binding: ActivityFilterBinding
    lateinit var viewModel: FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        viewModel = ViewModelProviders.of(this).get(FilterViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.navigator = this

        initToolbar()

        initListener()
    }

    fun initToolbar() {
        setSupportActionBar(binding.tbZigzagFilter)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_close_black_24dp
            )
        )
    }

    fun initListener() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return false
    }


    override fun onFilterFinishClick() {

        var intent = Intent()
        intent.putExtra("ageFilter",viewModel.ageFilter.toString())
        intent.putExtra("styleFilter",viewModel.styleFilter.toString())
        setResult(101,intent)

        finish();
    }
}
