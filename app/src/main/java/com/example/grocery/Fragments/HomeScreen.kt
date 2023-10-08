package com.example.grocery.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.BannerAdapter
import com.example.grocery.Adapters.VegesAdapter
import com.example.grocery.Cart
import com.example.grocery.Models.Fruits
import com.example.grocery.Models.VegesModel
import com.example.grocery.R
import com.example.grocery.SeeAllActivity

class HomeScreen : Fragment() {
    lateinit var bannerRecyclerView: RecyclerView
    lateinit var vegeList:ArrayList<VegesModel>
    lateinit var bannerList:ArrayList<Fruits>
    lateinit var vegesRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home_screen, container, false)
        view.findViewById<TextView>(R.id.menuOptions).setOnClickListener {
            val bootomDialog=BottomSheetShow(requireContext())
            bootomDialog.show()
        }





        bannerRecyclerView=view.findViewById(R.id.bannerView)
        vegesRecyclerView=view.findViewById(R.id.vegesView)
        vegeList= ArrayList()
        bannerList= ArrayList()
        vegeList.add(VegesModel(R.drawable.canvas,"Scenrie","seller: ","Mukesh"))
        vegeList.add(VegesModel(R.drawable.localart,"local","seller: ","Rakesh"))
        vegeList.add(VegesModel(R.drawable.canvas,"painting","seller: ","Ramesh"))
        vegeList.add(VegesModel(R.drawable.decors,"kuxh bhi","seller","Rajesh"))
        val layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        vegesRecyclerView.layoutManager=layoutManager
        val vegeadapter= VegesAdapter(requireContext(),vegeList)
        vegesRecyclerView.adapter=vegeadapter
        val layoutManager2= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        bannerRecyclerView.layoutManager=layoutManager2
        bannerList.add(Fruits(R.drawable.banner1))
        bannerList.add(Fruits(R.drawable.banner2))
        bannerList.add(Fruits(R.drawable.banner1))
        bannerList.add(Fruits(R.drawable.banner2))
        val adapterb= BannerAdapter(requireContext(),bannerList,0)
        bannerRecyclerView.adapter=adapterb
        view.findViewById<ImageView>(R.id.CartDis).setOnClickListener {
            startActivity(Intent(requireContext(), Cart::class.java))
        }
        view.findViewById<TextView>(R.id.seeAllveges).setOnClickListener {
            startActivity(Intent(requireContext(), SeeAllActivity::class.java))
        }
        view.findViewById<LinearLayout>(R.id.canvas).setOnClickListener {
            startActivity(Intent(requireContext(), SeeAllActivity::class.java))
        }
        view.findViewById<LinearLayout>(R.id.localArts).setOnClickListener {
            startActivity(Intent(requireContext(), SeeAllActivity::class.java))
        }
        view.findViewById<LinearLayout>(R.id.decorsLayout).setOnClickListener {
            startActivity(Intent(requireContext(), SeeAllActivity::class.java))
        }
        // Inflate the layout for requireContext() fragment
        return view

    }
}