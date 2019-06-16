package com.pawanjes.numbers.view.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.pawanjes.numbers.view.fragment.CarouselFragment
import com.pawanjes.numbers.service.model.ImageData

import java.util.*


class BannerAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {
    internal var banners: MutableList<ImageData>? = null

    init {
        banners = ArrayList()
    }


    override fun getItem(position: Int): Fragment {
        val content = CarouselFragment()
        val bundle = Bundle()
        bundle.putParcelable("imagedata", banners!![position])
        content.arguments = bundle
        return content
    }

    override fun getCount(): Int {
        return if (banners == null) 0 else banners!!.size
    }

    fun swapBanners(imageData: List<ImageData>) {
        banners!!.clear()
        this.banners!!.addAll(imageData)
        notifyDataSetChanged()
    }


}
