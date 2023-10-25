package com.example.grocery.Fragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.CartAdapter
import com.example.grocery.Helpers.AddItemsInCart
import com.example.grocery.Models.CartModel
import com.example.grocery.Models.CartsModel
import com.example.grocery.OrderConfirmed
import com.example.grocery.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        val finlamnt=view.findViewById<TextView>(R.id.FinalCart)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val btn = view.findViewById<Button>(R.id.PayBtn)
        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = lm
        val list = ArrayList<CartsModel>()
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val cartReference = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(currentUser.uid)
                .child("Cart")
            val load=
            cartReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var price=0
                    for (itemSnapshot in dataSnapshot.children) {
                        val cartsModel = itemSnapshot.getValue(CartsModel::class.java)
                        if (cartsModel != null) {
                            list.add(cartsModel)
                            price+=cartsModel.price
                            Log.d("CartItems adap", cartsModel.title)
                        }
                    }

                    // Create and set the adapter inside onDataChange when the list is populated
                    view.findViewById<TextView>(R.id.totalCart).setText(price.toString())
                    finlamnt.setText((price+5).toString())
                    btn.setText(finlamnt.text.toString())
                    val adapter = CartAdapter(requireContext(), list, btn)
                    recycler.adapter = adapter
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle any errors
                    val errorMessage = "Error: ${databaseError.message}"
                    Log.d("CartItems", errorMessage)
                }
            })
        } else {
            val errorMessage = "User is not authenticated."

        }


        Log.d("CartItems", finlamnt.text.toString())
        btn.setOnClickListener {
            startActivity(Intent(requireContext(), OrderConfirmed::class.java))
        }

        return view
    }
}

