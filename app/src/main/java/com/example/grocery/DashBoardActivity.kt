package com.example.grocery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.BannerAdapter
import com.example.grocery.Adapters.VegesAdapter
import com.example.grocery.Models.Fruits
import com.example.grocery.Models.VegesModel

class DashBoardActivity : AppCompatActivity() {
    lateinit var bannerRecyclerView:RecyclerView
    lateinit var typesRec:RecyclerView
    lateinit var vegeList:ArrayList<VegesModel>
    lateinit var typeList:ArrayList<Fruits>
    lateinit var bannerList:ArrayList<Fruits>
    lateinit var vegesRecyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        bannerRecyclerView=findViewById(R.id.bannerView)
        typesRec=findViewById(R.id.vegeTypesView)
        vegesRecyclerView=findViewById(R.id.vegesView)
        vegeList= ArrayList()
        typeList= ArrayList()
        bannerList= ArrayList()
        vegeList.add(VegesModel(R.drawable.vege1,"Apple","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Shimla Mirch","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"Kya Pta","1kg,","40"))
        vegeList.add(VegesModel(R.drawable.vege1,"kuxh bhi","1kg,","40"))
        val layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        vegesRecyclerView.layoutManager=layoutManager
        val vegeadapter=VegesAdapter(this,vegeList)
        vegesRecyclerView.adapter=vegeadapter

        typeList.add(Fruits(R.drawable.apple))
        typeList.add(Fruits(R.drawable.broccoli))
        typeList.add(Fruits(R.drawable.apple))
        typeList.add(Fruits(R.drawable.apple))
        typeList.add(Fruits(R.drawable.apple))
        val layoutManager1=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        typesRec.layoutManager=layoutManager1
        val adapter=BannerAdapter(this,typeList,1)
        typesRec.adapter=adapter

        val layoutManager2=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        bannerRecyclerView.layoutManager=layoutManager2
        bannerList.add(Fruits(R.drawable.banner1))
        bannerList.add(Fruits(R.drawable.banner2))
        bannerList.add(Fruits(R.drawable.banner1))
        bannerList.add(Fruits(R.drawable.banner2))
        val adapterb=BannerAdapter(this,bannerList,0)
        bannerRecyclerView.adapter=adapterb
        findViewById<ImageView>(R.id.CartDis).setOnClickListener {
            startActivity(Intent(this,Cart::class.java))
        }
        findViewById<TextView>(R.id.seeAllFru).setOnClickListener {
            startActivity(Intent(this,SeeAllActivity::class.java))
        }
        findViewById<TextView>(R.id.seeAllveges).setOnClickListener {
            startActivity(Intent(this,SeeAllActivity::class.java))
        }
    }
}