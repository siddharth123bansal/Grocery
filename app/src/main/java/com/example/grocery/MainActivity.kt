package com.example.grocery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

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
//        Toast.makeText(this,sd.getString("_id",null).toString(),Toast.LENGTH_SHORT).show()
        if(sd.getString("_id",null)!=null){
            startActivity(Intent(this,DashBoardActivity::class.java))
            finish()
        }
    }
}