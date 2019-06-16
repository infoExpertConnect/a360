package com.pawanjes.numbers.view.adapter

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.pawanjes.numbers.R
import com.pawanjes.numbers.view.activity.MainActivity
import com.pawanjes.numbers.service.model.ImageData

class FeedAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*init {
        var imageData: ArrayList<ImageData> = ArrayList()
        var im = ImageData()
        im.url = "https://picsum.photos/500/200?image=0"
        imageData.add(im)
        imageData.add(im)
        imageData.add(im)
        imageData.add(im)
        imageData.add(im)
        imageData.add(im)
        imageData.add(im)
        imageData.add(im)
        super.setBaseList(imageData)
        createOtherItemList()
    }*/

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewtype: Int): RecyclerView.ViewHolder {
        if (viewtype == 0) {
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.curosel_header_item_view, viewGroup, false)
            return BannersHolder(view)
        } else {
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.main_lead_item, viewGroup, false)
            return LeadViewHolder(view)
        }
    }


    /*override fun createOtherItemList() {
        val dummyObject1 = DummyObject(0, 0)
        super.otherViewPositions.add(dummyObject1)

    }
*/
    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder.itemViewType == 0) {
            val bannerAdapter = BannerAdapter((context as MainActivity).supportFragmentManager, context)
            val holder1 = viewHolder as BannersHolder
            holder1.bannerPager.adapter = bannerAdapter
            var imageData: ArrayList<ImageData> = ArrayList()
            var im: ImageData = ImageData()
            im.url = "https://picsum.photos/500/200?image=0"
            imageData.add(im)
            imageData.add(im)
            imageData.add(im)
            imageData.add(im)
            imageData.add(im)
            imageData.add(im)
            imageData.add(im)
            imageData.add(im)
            bannerAdapter.swapBanners(imageData)
            holder1.bannerPager.adapter = bannerAdapter
        }

    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    class LeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imageView: ImageView = itemView.findViewById(R.id.image_lead)
        internal var leadNamme: TextView = itemView.findViewById(R.id.lead_name)

    }

    inner class BannersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var bannerPager: ViewPager = itemView.findViewById(R.id.banner_list_viewpager)
        internal var tabLayout: TabLayout = itemView.findViewById(R.id.tabLayout)
        internal var curoselContainer: FrameLayout = itemView.findViewById(R.id.curosel_container)

        init {
            tabLayout.setupWithViewPager(bannerPager)
        }
    }
}