package com.pawanjes.numbers.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pawanjes.numbers.R
import com.pawanjes.numbers.viewmodel.TextViewModel
import kotlinx.android.synthetic.main.activity_about_contact_us.*

class AboutContactUsActivity : AppCompatActivity() {

    var textViewModel: TextViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_contact_us)
        textViewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)
        var type = intent.getIntExtra("type", 1)
        getContent(type)
    }

    private fun getContent(type: Int) {
        when (type) {
            1 -> {
                textViewModel!!.getAboutUs().observe(this, Observer {
                    if(it!=null){
                        tv_content.text = it.data?.content
                    }
                })
            }
            2 -> {
                textViewModel!!.getContactUs().observe(this, Observer {
                    if(it!=null){
                        tv_content.text = it.data?.content
                    }
                })
            }
        }
    }
}
