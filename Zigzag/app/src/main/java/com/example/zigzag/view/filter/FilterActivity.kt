package com.example.zigzag.view.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.zigzag.R
import com.example.zigzag.databinding.ActivityFilterBinding
import com.google.android.material.button.MaterialButtonToggleGroup

class FilterActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityFilterBinding
    lateinit var viewModel: FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        viewModel = ViewModelProviders.of(this).get(FilterViewModel::class.java)

        binding.viewmodel = viewModel

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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tob_ten_age -> Log.d("datacheck", "hi")
            R.id.tob_thirty_early_age -> Log.d("datacheck", "hi")
        }
    }


}
