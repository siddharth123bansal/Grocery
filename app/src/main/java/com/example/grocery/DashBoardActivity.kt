package com.example.grocery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.BannerAdapter
import com.example.grocery.Adapters.VegesAdapter
import com.example.grocery.Fragments.CartFragment
import com.example.grocery.Fragments.CommunityFragment
import com.example.grocery.Fragments.HomeScreen
import com.example.grocery.Fragments.ProfileFragment
import com.example.grocery.Models.Fruits
import com.example.grocery.Models.VegesModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
       val bottom=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setCurrentFragment(HomeScreen())
        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home->setCurrentFragment(HomeScreen())
                R.id.Community -> setCurrentFragment(CommunityFragment())
                R.id.Cart -> startActivity(Intent(this,Cart::class.java))
                R.id.Profile -> setCurrentFragment(ProfileFragment())
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}