package com.example.grocery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.VegesAdapter
import com.example.grocery.Models.VegesModel

class SeeAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all)
        val recycler=findViewById<RecyclerView>(R.id.MaterialsRecycler)
        val back=findViewById<ImageView>(R.id.backB)
        val tag=findViewById<TextView>(R.id.Tag)
        val vegeList=ArrayList<VegesModel>()
        vegeList.add(VegesModel(R.drawable.canvas,"Scenrie","seller: ","Mukesh"))
        vegeList.add(VegesModel(R.drawable.localart,"local","seller: ","Rakesh"))
        vegeList.add(VegesModel(R.drawable.canvas,"painting","seller: ","Ramesh"))
        vegeList.add(VegesModel(R.drawable.decors,"kuxh bhi","seller","Rajesh"))
        vegeList.add(VegesModel(R.drawable.canvas,"Scenrie","seller: ","Mukesh"))
        vegeList.add(VegesModel(R.drawable.localart,"local","seller: ","Rakesh"))
        vegeList.add(VegesModel(R.drawable.canvas,"painting","seller: ","Ramesh"))
        vegeList.add(VegesModel(R.drawable.decors,"kuxh bhi","seller","Rajesh"))
        vegeList.add(VegesModel(R.drawable.canvas,"Scenrie","seller: ","Mukesh"))
        vegeList.add(VegesModel(R.drawable.localart,"local","seller: ","Rakesh"))
        vegeList.add(VegesModel(R.drawable.canvas,"painting","seller: ","Ramesh"))
        vegeList.add(VegesModel(R.drawable.decors,"kuxh bhi","seller","Rajesh"))
        val lm=GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        recycler.layoutManager=lm
        val vegeadapter= VegesAdapter(this,vegeList)
        recycler.adapter=vegeadapter
    }
}