package com.example.grocery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.shopNow).setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        val sd=getSharedPreferences("groc", MODE_PRIVATE)
        if(sd.getString("number",null).toString().isNotEmpty()){
            startActivity(Intent(this,DashBoardActivity::class.java))
            finish()
        }
    }
}