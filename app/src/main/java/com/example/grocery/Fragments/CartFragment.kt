package com.example.grocery.Fragments
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.CartAdapter
import com.example.grocery.Models.CartModel
import com.example.grocery.OrderConfirmed
import com.example.grocery.R
class CartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cart, container, false)
        val recycler=view.findViewById<RecyclerView>(R.id.recyclerView)
        val btn=view.findViewById<Button>(R.id.PayBtn)
        val lm= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        recycler.layoutManager=lm
        val list=ArrayList<CartModel>()
        list.add(CartModel(R.drawable.canvas,"Painting","seller ","3","rakesh"))
        list.add(CartModel(R.drawable.localart,"Cup","seller ","4","ramesh"))
        list.add(CartModel(R.drawable.decors,"Jhula","seller ","2","sham"))
        list.add(CartModel(R.drawable.localart,"Bartan","seller ","1","raj"))
        val adapter= CartAdapter(requireContext(),list,btn)
        recycler.adapter=adapter
        btn.setOnClickListener {
            startActivity(Intent(requireContext(), OrderConfirmed::class.java))
        }
        return view
    }
}