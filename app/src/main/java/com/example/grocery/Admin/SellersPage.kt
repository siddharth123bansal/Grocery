package com.example.grocery.Admin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Admin.Adapters.SellersAdapter
import com.example.grocery.Admin.Models.UserData
import com.example.grocery.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SellersPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sellers_page)
        val sellerView=findViewById<RecyclerView>(R.id.SellerRecyclerView)
        val list=ArrayList<UserData>()

        val progressDialog = ProgressDialog(this@SellersPage)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("please wait...")
        progressDialog.show()
        FirebaseDatabase.getInstance().reference.child("RequestedSellers").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapshot in snapshot.children){
                    val data= itemSnapshot.getValue(UserData::class.java)
                    if (data != null) {
                        list.add(data)
                    }
                }
                progressDialog.dismiss()
                sellerView.layoutManager=LinearLayoutManager(this@SellersPage,LinearLayoutManager.VERTICAL,false)
                sellerView.adapter=SellersAdapter(this@SellersPage,list)
            }
            override fun onCancelled(error: DatabaseError) {
                progressDialog.dismiss()
                Toast.makeText(this@SellersPage,"Some error occured",Toast.LENGTH_SHORT).show()
            }

        })
    }
}