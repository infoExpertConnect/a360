package com.pawanjes.numbers.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.pawanjes.numbers.R
import com.pawanjes.numbers.view.adapter.FeedAdapter
import com.pawanjes.numbers.viewmodel.TextViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    var textViewModel: TextViewModel? = null
    var feedAdapter: FeedAdapter?=null
    override fun isHomeAsUpEnabled(): Boolean {
        return false
    }

    override fun isToolbarRequired(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

         feedAdapter = FeedAdapter(this)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        feedAdapter!!.setViewModel(textViewModel!!)
        rv_lead_cat.layoutManager = linearLayoutManager
        rv_lead_cat.adapter = feedAdapter

        fetchLeads()
    }

    private fun fetchLeads() {
        textViewModel!!.getMainLeads().observe(this, Observer {
            if(it?.data!=null){
                feedAdapter!!.setLeadData(it.data!!)
            }
        })

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                // Handle the camera action
            }
            R.id.nav_contact -> {
                var intent = Intent(this, AboutContactUsActivity::class.java)
                intent.putExtra("type", 2)
                startActivity(intent)
            }
            R.id.nav_about -> {
                var intent = Intent(this, AboutContactUsActivity::class.java)
                intent.putExtra("type", 1)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
