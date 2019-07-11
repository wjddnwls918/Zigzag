package com.example.zigzag.view.list

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.zigzag.R
import de.hdodenhof.circleimageview.CircleImageView

object ZigzagListBinding {

    @JvmStatic
    @BindingAdapter("inputImage")
    fun inputImage(view: CircleImageView, image: String) {

        val getDomain = image.substring(7, image.length)
        val domainSplit = getDomain.split(".")

        var result = "https://cf.shop.s.zigzag.kr/images/"

        if (domainSplit[0].equals("www")) {
            result += (domainSplit[1] +".jpg")
        } else {
            result += (domainSplit[0] +".jpg")
        }

        Glide.with(view.context)
            .load(result)
            .error(R.drawable.noimage)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("transAge")
    fun transAge(view: TextView, age: Array<Int>) {

        val ageArray = arrayOf(0, 0, 0)

        for ((index, value) in age.withIndex()) {
            if (value == 1) {
                when (index) {
                    0 -> ageArray[0] += 1
                    1, 2, 3 -> ageArray[1] += 1
                    4, 5, 6 -> ageArray[2] += 1
                }
            }
        }


        var result = ""
        for ((index, value) in ageArray.withIndex()) {
            if (value > 0) {
                when (index) {
                    0 -> result += "10대 "
                    1 -> result += "20대 "
                    2 -> result += "30대 "
                }
            }
        }

        view.setText(result)

    }
}