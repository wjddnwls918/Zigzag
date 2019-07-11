package com.example.zigzag.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.zigzag.common.BaseRecyclerVIewAdapter
import com.example.zigzag.databinding.ItemZigzagListBinding
import com.example.zigzag.model.list.Shop

class ZigzagListAdapter(dataSet: MutableList<Shop>) : BaseRecyclerVIewAdapter<Shop, ZigzagListAdapter.ViewHolder>(dataSet) {

    override fun onBindView(holder: ViewHolder, position: Int) {
        holder.binding.shop = getItem(position)
        holder.binding.rank = (position+1).toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemZigzagListBinding = ItemZigzagListBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return ViewHolder(binding)
    }


    class ViewHolder(var binding: ItemZigzagListBinding) : RecyclerView.ViewHolder(binding.root)

}