package com.example.zigzag.view.filter

import androidx.lifecycle.ViewModel

class FilterViewModel : ViewModel() {

    var ageFilter = 0
    var styleFilter = 0

    lateinit var navigator: FilterNavigator

    fun onAgeFilterClick(position: Int) {

        if( (ageFilter and (1 shl position)) != (1 shl position)) {
            ageFilter = ageFilter or (1 shl position)
        } else {
            ageFilter = ageFilter and (1 shl position).inv()
        }
    }

    fun onStyleFilterClick(position: Int) {

        if( (styleFilter and (1 shl position)) != (1 shl position)) {
            styleFilter = styleFilter or (1 shl position)
        } else {
            styleFilter = styleFilter and (1 shl position).inv()
        }
    }

    fun onFilterFinishClick() {
        navigator.onFilterFinishClick()
    }

}