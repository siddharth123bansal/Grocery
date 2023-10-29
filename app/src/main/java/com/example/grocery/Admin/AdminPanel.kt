package com.example.grocery.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.grocery.R

class AdminPanel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)
        findViewById<LinearLayout>(R.id.SellerBtn).setOnClickListener {
            startActivity(Intent(this@AdminPanel,SellersPage::class.java))
        }
    }
}