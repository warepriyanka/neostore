package com.example.neostoreapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostoreapplication.Adapter.NavigationAdapter
import com.example.neostoreapplication.ViewModel.NavigationModel
import com.example.neostoreapplication.R
import com.example.neostoreapplication.utils.SessionManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.item_banner_images.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var navigationAdapter: NavigationAdapter?=null
    val navigationModel:ArrayList<NavigationModel> = ArrayList()

    private var mSectionsPagerAdapter:  SectionPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle("NeoSTORE")

        val sessioManger= SessionManager(applicationContext)
        Log.e("token",sessioManger.getToken())
//Toast.makeText(this,sessioManger.getToken(),Toast.LENGTH_LONG).show()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_tools,
                R.id.nav_share,
                R.id.nav_send
            ), drawerLayout
        )
        mSectionsPagerAdapter = SectionPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        bannerViewPager.adapter = mSectionsPagerAdapter
        dotTab_layout.setupWithViewPager(bannerViewPager,true)
        autoScroll()
        perpareNavData()
        navigationAdapter = NavigationAdapter(this,navigationModel)
        val recyclerViewLayOutManager = LinearLayoutManager(applicationContext)
        rv_drawer.layoutManager = recyclerViewLayOutManager
        rv_drawer.adapter=navigationAdapter
        tableRelativeLayout.setOnClickListener(this)
        sofasRelativeLayout.setOnClickListener(this)
        chairRelativeLayout.setOnClickListener(this)
        cupboardsRelativeLayout.setOnClickListener(this)

    }

    override fun onClick(v: View?){
        when(v?.id)
        {
            R.id.tableRelativeLayout ->{
                val tableIntent = Intent(this, ProductListActivity::class.java)
                tableIntent.putExtra("product_id", "1")
                tableIntent.putExtra("product_name", "Tables")
                startActivity(tableIntent)
            }
            R.id.sofasRelativeLayout ->{
                val tableIntent = Intent(this, ProductListActivity::class.java)
                tableIntent.putExtra("product_id", "3")
                tableIntent.putExtra("product_name", "Sofas")
                startActivity(tableIntent)
            }
            R.id.chairRelativeLayout ->{
                val tableIntent = Intent(this, ProductListActivity::class.java)
                tableIntent.putExtra("product_id", "2")
                tableIntent.putExtra("product_name", "Chairs")
                startActivity(tableIntent)
            }
            R.id.cupboardsRelativeLayout ->{
                val tableIntent = Intent(this, ProductListActivity::class.java)
                tableIntent.putExtra("product_id", "4")
                tableIntent.putExtra("product_name", "Cupboards")
                startActivity(tableIntent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    fun autoScroll()
    {
        var currentPage=0

        val timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                bannerViewPager.post(object :Runnable{
                    override fun run() {
                        if (bannerViewPager.currentItem==3)
                        {
                            bannerViewPager.setCurrentItem(0)
                        }
                        else{
                            bannerViewPager.setCurrentItem(bannerViewPager.currentItem+1)
                        }

                    }
                })
                //To change body of created functions use File | Settings | File Templates.
            }
        },500,3000)

    }

    fun perpareNavData() {
        var navData = NavigationModel(
            R.drawable.shopping_cart,
            "My Cart",
            "2"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.table,
            "Tables",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.sofa,
            "Sofas",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.chair,
            "Chairs",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.cupboard,
            "CupBoards",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.username_icon,
            "My Account",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.storelocator_icon,
            "Store Locator",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.myorders_icon,
            "My Orders",
            "0"
        )
        navigationModel.add(navData)
        navData = NavigationModel(
            R.drawable.logout_icon,
            "Logout",
            "0"
        )
        navigationModel.add(navData)

    }


    inner class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm)
    {
        override fun getCount(): Int {
            return 4
        }

        override fun getItem(position: Int): Fragment {
            return PlaceHolderFragment.newInstance(
                position + 1
            )
        }

    }

    class PlaceHolderFragment : Fragment()
    {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.item_banner_images,container,false)
            if (arguments!!.getInt(ARG_SECTION_NUMBER)==1)
            {
                rootView.bannerImageView.setImageResource(R.drawable.slider_img1)
                rootView.imgcount.setText(arguments!!.getInt(ARG_SECTION_NUMBER).toString()+"/"+"4")
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER)==2)
            {
                rootView.bannerImageView.setImageResource(R.drawable.slider_img2)
                rootView.imgcount.setText(arguments!!.getInt(ARG_SECTION_NUMBER).toString()+"/"+ "4")

            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER)==3)
            {
                rootView.bannerImageView.setImageResource(R.drawable.slider_img3)
                rootView.imgcount.setText(arguments!!.getInt(ARG_SECTION_NUMBER).toString()+"/"+ "4")

            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER)==4)
            {
                rootView.bannerImageView.setImageResource(R.drawable.slider_img4)
                rootView.imgcount.setText(arguments!!.getInt(ARG_SECTION_NUMBER).toString()+"/"+ "4")

            }


            return rootView
        }
        //companion objct can be access driectly via name without calling name of class and we can aldo call name of class too
        companion object
        {
            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber:Int): PlaceHolderFragment
            {
                val fragment= PlaceHolderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER,sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}
