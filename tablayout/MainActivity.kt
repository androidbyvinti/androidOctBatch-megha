package com.bmpl.tablayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var images = arrayOf(android.R.drawable.sym_contact_card, android.R.drawable.ic_menu_call)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // viewpager --> pages --> adapter --> FragmentPageAdapter
        // ListView



        setFragments(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        setIcons()
    }

    fun setIcons(){
        tabLayout.getTabAt(0)!!.setIcon(images[0])
        tabLayout.getTabAt(1)!!.setIcon(images[1])
    }

    fun setFragments(viewPager: ViewPager){

        var fragmentData  = FragmentData(supportFragmentManager)  // FragmentData
        fragmentData.add(ContactsFragment(), "Contacts")  // 2 parameters --> 1 parameter --> Fragment and 2nd parameter --> String
        fragmentData.add(CallsFragment(), "Calls")
        viewPager.adapter = fragmentData
    }

    class FragmentData(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        var fragmentList : ArrayList<Fragment> = ArrayList()
        var titleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return titleList.size
        }

        fun add(fragment : Fragment, title : String){
                fragmentList.add(fragment)
                titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            //return ""
            return titleList[position]
        }
    }
}

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
//                    .setAction("Action", View.OnClickListener {
//                        Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show()
//                    }).show()
//        }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings ->
//            {
//                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
//                true
//            }
//            R.id.about_us ->
//            {
//                val layoutInflater : LayoutInflater = this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//                val toast = Toast(this)
//                val view : View = layoutInflater.inflate(R.layout.custom_toast, relativeLayout)
//                toast.duration = Toast.LENGTH_SHORT
//                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
//                toast.view = view
//                toast.show()
//                //Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
