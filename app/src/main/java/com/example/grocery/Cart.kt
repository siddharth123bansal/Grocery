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
        list.add(CartModel(R.drawable.vege1,"Mirch","1kg","3","60"))
        list.add(CartModel(R.drawable.vege1,"Mirch","1kg","4","40"))
        list.add(CartModel(R.drawable.vege1,"Mirch","1kg","2","30"))
        list.add(CartModel(R.drawable.vege1,"Mirch","1kg","1","50"))
        val adapter=CartAdapter(this,list,btn)
        recycler.adapter=adapter
        btn.setOnClickListener {
            startActivity(Intent(this,OrderConfirmed::class.java))
        }
    }
}