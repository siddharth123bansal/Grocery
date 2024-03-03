package com.example.grocery.Admin

import android.app.Activity
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Admin.Adapters.BiddingAdapter
import com.example.grocery.Admin.Models.NewBidingModel
import com.example.grocery.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text
import java.util.Calendar
import java.util.Random

class AddBiding : AppCompatActivity() {
    private lateinit var selectedImage:String
    private lateinit var image:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_biding)
        val bidinglayout=findViewById<LinearLayout>(R.id.AddBidingLayout)
        image=findViewById(R.id.bidingImage)
         selectedImage=""
        val name=findViewById<EditText>(R.id.bidingName)
        val description=findViewById<EditText>(R.id.bidingDescription)
        val price=findViewById<EditText>(R.id.bidingBasePrice)
        val startdate=findViewById<TextView>(R.id.bidingstartingDate)
        val endingdate=findViewById<TextView>(R.id.bidingEndingDate)
        val submit=findViewById<Button>(R.id.submitBtn)
        val list= ArrayList<NewBidingModel>()
        val recyclerview=findViewById<RecyclerView>(R.id.bidingRecyclerView)

        val type=intent.getStringExtra("type")
        if(type=="add"){
            recyclerview.visibility=View.GONE
            bidinglayout.visibility= View.VISIBLE
        }else{
            bidinglayout.visibility= View.GONE
            recyclerview.visibility=View.VISIBLE
            val progressDialog = ProgressDialog(this@AddBiding)
            progressDialog.setCancelable(false)
            progressDialog.setMessage("please wait...")
            progressDialog.show()
            FirebaseDatabase.getInstance().reference.child("Bidings").addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(data in snapshot.children){
                        val bid=data.getValue(NewBidingModel::class.java)
                        if (bid != null) {
                            list.add(bid)
                        }
                    }
                    progressDialog.dismiss()
                    recyclerview.adapter=BiddingAdapter(this@AddBiding,list)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("RecyclerError",error.message.toString())
                }

            })
        }
        startdate.setOnClickListener {
            showDatePickerDialog(startdate)
        }
        endingdate.setOnClickListener {
            showDatePickerDialog(endingdate)
        }
        image.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*" // Set the MIME type or use specific types like "image/*", "application/pdf", etc.
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 101)
        }
        submit.setOnClickListener {
            val progressDialog = ProgressDialog(this@AddBiding)
            progressDialog.setCancelable(false)
            progressDialog.setMessage("please wait...")
            progressDialog.show()
            if(selectedImage.isEmpty()){
                progressDialog.dismiss()
                Toast.makeText(this@AddBiding,"Painting Image is required",Toast.LENGTH_SHORT).show()
            }else if(name.text.toString().trim().isEmpty()){
                progressDialog.dismiss()
                Toast.makeText(this@AddBiding,"Painting Name is required",Toast.LENGTH_SHORT).show()
            }else if(description.text.toString().trim().isEmpty()){
                progressDialog.dismiss()
                Toast.makeText(this@AddBiding,"Painting Description is required",Toast.LENGTH_SHORT).show()
            }else if(price.text.toString().trim().isEmpty()){
                progressDialog.dismiss()
                Toast.makeText(this@AddBiding,"Painting Biding Base Price is required",Toast.LENGTH_SHORT).show()
            }else if(startdate.text.toString().isEmpty()){
                progressDialog.dismiss()
                Toast.makeText(this@AddBiding,"Biding Start Date is required",Toast.LENGTH_SHORT).show()
            }else if(endingdate.text.toString().trim().isEmpty()){
                progressDialog.dismiss()
                Toast.makeText(this@AddBiding,"Biding End Date is required",Toast.LENGTH_SHORT).show()
            }else{
                progressDialog.show()
                val id=generate16DigitNumber()
                FirebaseDatabase.getInstance().reference.child("Bidings").child(id).setValue(NewBidingModel(id,selectedImage,name.text.toString().trim(),description.text.toString().trim(),Integer.parseInt(price.text.toString().trim()), startdate.text.toString().trim(), endingdate.text.toString().trim()))
                progressDialog.dismiss()
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val progressDialog = ProgressDialog(this@AddBiding)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("please wait...")
        progressDialog.show()
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val selectedFileUri: Uri? = data.data
                val refrence=FirebaseStorage.getInstance().reference.child("bidings").child(generate16DigitNumber())
                refrence.putFile(selectedFileUri!!).addOnSuccessListener {
                    refrence.downloadUrl.addOnSuccessListener {
                        selectedImage=it.toString()
                        image.setImageURI(selectedFileUri)
                        progressDialog.dismiss()
                    }
                }
            }
        } else{
            progressDialog.dismiss()
            Toast.makeText(this,"Some error occured try again later",Toast.LENGTH_SHORT).show()
        }
    }
    fun generate16DigitNumber(): String {
        val random = Random()
        val sb = StringBuilder(16)

        for (i in 0 until 16) {
            val digit = random.nextInt(10)
            sb.append(digit)
        }
        return sb.toString()
    }
    private fun showDatePickerDialog(dateTextView: TextView) {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view: DatePicker, year, monthOfYear, dayOfMonth ->
                // Month is 0-based, so add 1 to it
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                dateTextView.text = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}