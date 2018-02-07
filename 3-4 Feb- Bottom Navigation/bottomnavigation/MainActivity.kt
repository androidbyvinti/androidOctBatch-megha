package com.bmpl.android.bottomnavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bbn_item1 -> {

                // bind fragment with activity
                //step-1: Call the Fragment Manager

                var syncFragment = SyncFragment()

                supportFragmentManager.beginTransaction().replace(R.id.fragmentView, syncFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.bbn_item2 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.bbn_item3 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.action4 -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "inside onCreate()")
        //BottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "inside onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "inside onResume()")
    }
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "inside onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "inside onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "inside onDestroy()")
    }

}
