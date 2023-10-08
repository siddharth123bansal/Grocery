package com.example.grocery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.grocery.Adapters.CartAdapter
import com.example.grocery.Models.CartModel

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val recycler=findViewById<RecyclerView>(R.id.recyclerView)
        val btn=findViewById<Button>(R.id.PayBtn)
        val lm=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycler.layoutManager=lm
        val list=ArrayList<CartModel>()
        list.add(CartModel(R.drawable.canvas,"Painting","seller ","3","rakesh"))
        list.add(CartModel(R.drawable.localart,"Cup","seller ","4","ramesh"))
        list.add(CartModel(R.drawable.decors,"Jhula","seller ","2","sham"))
        list.add(CartModel(R.drawable.localart,"Bartan","seller ","1","raj"))
        val adapter=CartAdapter(this,list,btn)
        recycler.adapter=adapter
        btn.setOnClickListener {
            startActivity(Intent(this,OrderConfirmed::class.java))
        }
    }
}