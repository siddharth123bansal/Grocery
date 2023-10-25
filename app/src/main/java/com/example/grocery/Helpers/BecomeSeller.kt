package com.example.grocery.Helpers

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.example.grocery.Models.BecomeSeller
import com.example.grocery.Models.NewUser
import com.example.grocery.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileOutputStream

class BecomeSeller : AppCompatActivity() {
    lateinit var idproof:String
    lateinit var profile:String
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_seller)
        val name=findViewById<EditText>(R.id.nameTxt)
        val mobile =findViewById<EditText>(R.id.mobileTxt)
        val govid=findViewById<TextView>(R.id.GovidTxt)
        val uploadbtn=findViewById<Button>(R.id.uploadBtn)
        val addressline1=findViewById<EditText>(R.id.Address1Txt)
        val addressline2=findViewById<EditText>(R.id.address2Txt)
        val country=findViewById<EditText>(R.id.countryIp)
        val state=findViewById<EditText>(R.id.stateIp)
        val city=findViewById<EditText>(R.id.cityIp)
        val zipcode=findViewById<EditText>(R.id.zipcodeTxt)
        val sellerBtn=findViewById<Button>(R.id.sellerSubmit)
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("please wait...")
        uploadbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*" // Set the MIME type or use specific types like "image/*", "application/pdf", etc.
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 101)

        }
        findViewById<Button>(R.id.uploadprofileBtn).setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*" // Set the MIME type or use specific types like "image/*", "application/pdf", etc.
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 102)
        }
        govid.setOnClickListener {
            showPopupMenu(govid)
        }
        sellerBtn.setOnClickListener {
            if(name.text.toString().isNullOrBlank()||mobile.text.toString().isNullOrBlank()||govid.text.toString().isNullOrBlank()||addressline1.text.toString().isNullOrBlank()||addressline2.text.toString().isNullOrBlank()||country.text.toString().isNullOrBlank()||state.text.toString().isNullOrBlank()||city.text.toString().isNullOrBlank()||zipcode.text.toString().isNullOrBlank()){
                Toast.makeText(this,"All Fields are required",Toast.LENGTH_SHORT).show()
            }
            else  if(profile.isEmpty()) {
                Toast.makeText(
                    this,
                    "Profile Image Image is required to become a seller",
                    Toast.LENGTH_SHORT
                ).show()
            }else if(idproof.isEmpty()) {
                Toast.makeText(
                    this,
                    "Id Proof Image is required to become a seller",
                    Toast.LENGTH_SHORT
                ).show()

            }else{
                progressDialog.show()
                val id =FirebaseAuth.getInstance().currentUser?.uid.toString()
                FirebaseDatabase.getInstance().reference.child("RequestedSellers").child(id).setValue(BecomeSeller(id,profile,name.text.toString(),mobile.text.toString(),"aadhar card",idproof,addressline1.text.toString(),
                    addressline2.text.toString(),country.text.toString(),state.text.toString(),city.text.toString(),zipcode.text.toString()))
                FirebaseDatabase.getInstance().reference.child("Users").child(id).child("seller").setValue("requested")
                progressDialog.dismiss()
                Toast.makeText(this@BecomeSeller,"Your Request to Become Seller is submited successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        progressDialog.show()
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val selectedFileUri: Uri? = data.data
                val refrence=FirebaseStorage.getInstance().reference.child("IdProofs").child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                refrence.putFile(selectedFileUri!!).addOnSuccessListener {
                    refrence.downloadUrl.addOnSuccessListener {
                        idproof=it.toString()
                        findViewById<TextView>(R.id.textView19).setText("Uploaded Successfully")
                        progressDialog.dismiss()
                    }
                }
            }
        }else if(requestCode == 102 && resultCode == Activity.RESULT_OK){
            if(data!=null){
                val selectedFileUri: Uri? = data.data
                val refrence=FirebaseStorage.getInstance().reference.child("ProfilePhotos").child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                refrence.putFile(selectedFileUri!!).addOnSuccessListener {
                    refrence.downloadUrl.addOnSuccessListener {
                        profile=it.toString()
                        findViewById<TextView>(R.id.textViewProfile).setText("Uploaded Successfully")
                        progressDialog.dismiss()
                    }
                }

            }
        }else{
            Toast.makeText(this,"Some error occured try again later",Toast.LENGTH_SHORT).show()
        }
    }
    private fun showPopupMenu(textView: TextView) {
        val popupMenu = PopupMenu(this,textView )
        popupMenu.inflate(R.menu.idproof_type)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.addhar -> {
                    textView.setText("Aadhar Card")
                    true
                }
                R.id.pan->{
                    textView.setText("Pan Card")
                    true
                }
                R.id.voter->{
                textView.setText("Voter Id card")
                true
                }
                R.id.rashan->{
                textView.setText("Rashan Card")
                true
            }
                else -> false
            }
        }
        popupMenu.show()
    }
}