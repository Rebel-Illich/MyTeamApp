package com.hunter.myteams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.hunter.myteams.adapterViewPager.ViewPagerAdapter
import com.hunter.myteams.fragmentCompanies.MyCompanyFragment
import com.hunter.myteams.fragmentMyTeam.MyTeamFragment
import com.hunter.myteams.fragmentAllTeams.AllTeamFragment

class MainActivity : AppCompatActivity() {

    private lateinit var pTabs: TabLayout
    private lateinit var pViewPager:ViewPager
    private lateinit var pagerAdapters: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            pTabs = findViewById(R.id.tabs)

            pViewPager = findViewById(R.id.myPagerView)
            pagerAdapters = ViewPagerAdapter(supportFragmentManager)

            pagerAdapters.addFragment(MyCompanyFragment(),"My Company")
            pagerAdapters.addFragment(AllTeamFragment(),"All Teams")
            pagerAdapters.addFragment(MyTeamFragment(),"My Team")

            pViewPager.adapter = pagerAdapters

            pTabs.setupWithViewPager(pViewPager)
        }
}


