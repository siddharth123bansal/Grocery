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
        vegeList.add(VegesModel(R.drawable.vege1,"Apple","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Banana","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Carrot","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"kiwi","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"orange","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"cookie","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Onion","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Adrak","1kg,","40"))
        val lm=GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        recycler.layoutManager=lm
        val vegeadapter= VegesAdapter(this,vegeList)
        recycler.adapter=vegeadapter
    }
}