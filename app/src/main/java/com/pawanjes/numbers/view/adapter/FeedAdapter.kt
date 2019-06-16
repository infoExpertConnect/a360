package com.pawanjes.numbers.view.adapter

import android.arch.lifecycle.Observer
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
import com.pawanjes.numbers.service.model.ImageData
import com.pawanjes.numbers.service.model.LeadResponse
import com.pawanjes.numbers.view.activity.MainActivity
import com.pawanjes.numbers.viewmodel.TextViewModel

class FeedAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var textViewModel: TextViewModel? = null
    var bannerList = arrayListOf<String>()
    var leadList = arrayListOf<LeadResponse>()
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewtype: Int): RecyclerView.ViewHolder {
        if (viewtype == 0) {
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.curosel_header_item_view, viewGroup, false)
            return BannersHolder(view)
        } else {
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.main_lead_item, viewGroup, false)
            return LeadViewHolder(view)
        }
    }

    fun setLeadData(leadlist : ArrayList<LeadResponse>){
        this.leadList.addAll(leadlist)
        notifyDataSetChanged()
    }
    fun getBanners(){
        textViewModel!!.getBanners().observe(context as MainActivity, Observer {
            if (it?.data != null) {
                this.bannerList = it.data!!.banners as ArrayList<String>
                notifyDataSetChanged()
            }
        })
    }
    override fun getItemCount(): Int {
        return leadList.size+1
    }

    fun setViewModel(textViewModel: TextViewModel) {
        this.textViewModel = textViewModel
        getBanners()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder.itemViewType == 0) {
            val bannerAdapter = BannerAdapter((context as MainActivity).supportFragmentManager, context)
            val holder1 = viewHolder as BannersHolder
            holder1.bannerPager.adapter = bannerAdapter
            var imageData: ArrayList<ImageData> = ArrayList()
            for (item in bannerList) {
                var im: ImageData = ImageData()
                im.url = item
                imageData.add(im)
            }
            bannerAdapter.swapBanners(imageData)
            holder1.bannerPager.adapter = bannerAdapter
        }
        else{
            val holder1 = viewHolder as LeadViewHolder
            holder1.leadNamme.text = leadList[position-1].name+" with id "+leadList[position-1].id
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