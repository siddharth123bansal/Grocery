package com.example.grocery

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.grocery.Models.NewUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CreateAccount : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        auth= FirebaseAuth.getInstance()
        findViewById<TextView>(R.id.loginUser).setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            val email=findViewById<EditText>(R.id.editTextEmail).text.toString()
            val password=findViewById<EditText>(R.id.otp).text.toString()
            val mobile=findViewById<EditText>(R.id.editTextPhone).text.toString()
            val name=findViewById<EditText>(R.id.editTextName).text.toString()
            if(email.isNullOrBlank() || password.isNullOrBlank() || mobile.isNullOrBlank() || name.isNullOrBlank()){
                Toast.makeText(this,"All Feilds are required",Toast.LENGTH_SHORT).show()
            }else if(mobile.length<10){
                Toast.makeText(this,"Enter a valid 10 digit number",Toast.LENGTH_SHORT).show()
            }else{
                signUpUser(email,password,mobile,name)
            }
        }
    }

    private fun signUpUser(email: String, password: String, mobile: String, name: String) {
        val progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...") // Set the message to display
        progressDialog?.setCancelable(false) // Prevent users from dismissing the dialog by tapping outside
        progressDialog?.show()
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = NewUser(name,email,password,mobile,"no")
                    FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser?.uid.toString()).setValue(user)
                    progressDialog.dismiss()
                    Toast.makeText(
                        baseContext,
                        "User Created Succesfully",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val sd=getSharedPreferences("groc", MODE_PRIVATE)
                    val edit=sd.edit()
                    edit.putString("_id",auth.currentUser?.uid.toString())
                    edit.putString("number",mobile)
                    edit.putString("email",email)
                    edit.putString("name",name)
                    edit.putString("isSeller","no")
                    edit.commit()
                    startActivity(Intent(this,DashBoardActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    progressDialog.dismiss()
                    Toast.makeText(
                        baseContext,
                        task.exception?.message.toString(),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}