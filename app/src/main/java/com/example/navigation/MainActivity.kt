package com.example.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var navController: NavController


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupNavigation()
        setEvents()
    }

    private fun setEvents() {
        drawer_top_page.setOnClickListener(this)
        drawer_user_info.setOnClickListener(this)
        drawer_push_notification.setOnClickListener(this)
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        navController = Navigation.findNavController(this, R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            @Suppress("DEPRECATION")
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                drawerLayout.setScrimColor(resources.getColor(android.R.color.transparent))
                content.translationX = drawerView.width * slideOffset
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onClick(p0: View?) {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (p0?.id) {
            R.id.drawer_top_page -> navController.navigate(R.id.AFragment)
            R.id.drawer_user_info -> navController.navigate(R.id.BFragment)
            R.id.drawer_push_notification -> navController.navigate(R.id.CFragment)
        }
    }
}
