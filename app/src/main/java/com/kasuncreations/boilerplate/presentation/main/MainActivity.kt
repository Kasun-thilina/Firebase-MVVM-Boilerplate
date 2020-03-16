package com.kasuncreations.boilerplate.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.kasuncreations.boilerplate.R
import com.kasuncreations.boilerplate.presentation.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var bottomAppBar: BottomAppBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        bottomAppBar = findViewById(R.id.bottom_nav)
        setSupportActionBar(bottomAppBar)
        nav_btn_profile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.newInstance(), "profile")
                .commit()
        }

        // bottomAppBar.replaceMenu(R.menu.app_bar_menu)
        /*bottomAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.nav_btn_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,ProfileFragment.newInstance(),"profile")
                        .commit()

                }
            }
            false
        }*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_btn_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment.newInstance(), "profile")
                    .commit()
            }
        }

        return super.onOptionsItemSelected(item)
    }


}
