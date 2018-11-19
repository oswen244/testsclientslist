package com.test.testclientslist.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.test.testclientslist.R
import com.test.testclientslist.ui.fragments.DashboardFragment
import com.test.testclientslist.utils.LocalStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.databinding.adapters.CompoundButtonBindingAdapter.setChecked
import android.R.attr.fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import com.test.testclientslist.ui.fragments.ProspectsListFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var local: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        local = LocalStorage(this)

        displayView(R.id.nav_dashboard)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displayView(item.itemId)
        setTitle(item.getTitle());
        return true
    }

    fun displayView(viewId: Int) {

        var fragment: Fragment? = null

        when (viewId) {
            R.id.nav_dashboard -> {
                fragment = DashboardFragment()
            }

            R.id.nav_clients -> {
                fragment = ProspectsListFragment()
            }

            R.id.nav_logout -> {
                logout()
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.replace(R.id.content, fragment)
            ft.commit()
        }

        drawer_layout.closeDrawer(GravityCompat.START)

    }


    fun logout(){
        local.clearTokenData()
        val intent: Intent = Intent(this, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
