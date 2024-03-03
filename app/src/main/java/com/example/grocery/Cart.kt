package com.example.grocery

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Adapters.CartAdapter
import com.example.grocery.Models.CartsModel
import com.razorpay.PaymentResultListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.razorpay.Checkout
import org.json.JSONObject

class Cart : AppCompatActivity(),PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID("rzp_test_2baWuUPou8lJzA")
        //http://192.168.1.36:3000/

        val finlamnt=findViewById<TextView>(R.id.FinalCart)
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        val btn = findViewById<Button>(R.id.PayBtn)
        val lm = LinearLayoutManager(this@Cart, LinearLayoutManager.VERTICAL, false)
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
                        findViewById<TextView>(R.id.totalCart).setText(price.toString())
                        finlamnt.setText((price+5).toString())
                        btn.setText(finlamnt.text.toString())
                        val adapter = CartAdapter(this@Cart, list, btn)
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
            startPayment()
//            startActivity(Intent(requireContext(), OrderConfirmed::class.java))
        }

    }
    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successfully:", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Error in payment: ", Toast.LENGTH_LONG).show()
    }
    private fun startPayment() {
        /*
        *  You need to pass the current activity to let Razorpay create CheckoutActivity
        * */
        val amount =5000
        Checkout.preload(applicationContext)
//        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("rzp_test_2baWuUPou8lJzA")

        try {
            val options = JSONObject()
            options.put("name","Siddharth Bansal")
            options.put("description","Paying for your order")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#4b61a1");
            options.put("currency","INR");
            options.put("order_id", "order_Nhuhlf8umFB2NF");
            options.put("amount","${(amount*100)}")//pass amount in currency subunits

            val retryObj =  JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","siddharth@gmail.com")
            prefill.put("contact","8824999745")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
            Log.d("RazorPay",e.message.toString())
            e.printStackTrace()
        }
    }

}