package com.example.grocery.Helpers

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.grocery.Models.CartsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONObject
import java.io.File

class AddItemsInCart {
    interface CartItemsCallback {
        fun onCartItemsRetrieved(cartItems: ArrayList<CartsModel>)
        fun onError(errorMessage: String)
    }
    fun getCartItems(callback: CartItemsCallback):ArrayList<CartsModel> {
        val cartItems = ArrayList<CartsModel>()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            cartItems.clear()
            val cartReference = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(currentUser.uid)
                .child("Cart")
            cartReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (itemSnapshot in dataSnapshot.children) {
                        val cartsModel = itemSnapshot.getValue(CartsModel::class.java)
                        if (cartsModel != null) {
                            cartItems.add(cartsModel)
                            Log.d("CartItems",cartsModel.title)
                        }
                    }
                    callback.onCartItemsRetrieved(cartItems)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle any errors
                    val errorMessage = "Error: ${databaseError.message}"
                    Log.d("CartItems",errorMessage)
                    callback.onError(errorMessage)
                }
            })
        } else {
            val errorMessage = "User is not authenticated."
            Log.d("CartItems", errorMessage)
            callback.onError(errorMessage)
        }
        Log.d("CartItems",cartItems.toString())
        return cartItems
    }


    fun becomeASeller(file:File){
    }



    fun insertItem(image:Int,productName:String,sellerName:String,price:Int,context: Context):String {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        val currentUser = FirebaseAuth.getInstance().currentUser
        progressDialog.show()
        if (currentUser != null) {
            val cartReference = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(currentUser.uid)
                .child("Cart")

            val cartsModel = CartsModel(image, productName, sellerName, 1, price)
            cartReference.push().setValue(cartsModel)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        Toast.makeText(context,"Item Added Successfully",Toast.LENGTH_SHORT).show()
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(context,"An Error Occurred: ${task.exception?.message}",Toast.LENGTH_SHORT).show()

                    }
                }
        } else {
            progressDialog.dismiss()
            Toast.makeText(context,"User is not authenticated",Toast.LENGTH_SHORT).show()
        }
        return ""
    }
}