package com.example.grocery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.VegesAdapter
import com.example.grocery.Models.VegesModel

class DashBoardActivity : AppCompatActivity() {
    lateinit var bannerRecyclerView:RecyclerView
    lateinit var typesRec:RecyclerView
    lateinit var vegeList:ArrayList<VegesModel>
    lateinit var vegesRecyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        bannerRecyclerView=findViewById(R.id.bannerView)
        typesRec=findViewById(R.id.vegeTypesView)
        vegesRecyclerView=findViewById(R.id.vegesView)
        vegeList= ArrayList()
        vegeList.add(VegesModel(R.drawable.vege1,"Apple","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Shimla Mirch","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Kya Pta","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"kuxh bhi","1kg,","40"))
        val layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        vegesRecyclerView.layoutManager=layoutManager
        val vegeadapter=VegesAdapter(this,vegeList)
        vegesRecyclerView.adapter=vegeadapter

    }
}