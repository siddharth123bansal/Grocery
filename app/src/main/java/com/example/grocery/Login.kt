package com.example.grocery

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.grocery.Admin.AdminPanel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var phonenum:EditText
    private lateinit var pass:EditText
    private lateinit var submit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phonenum=findViewById(R.id.editTextPhone)
        pass=findViewById(R.id.textPassword)
        submit=findViewById(R.id.button2)
        findViewById<TextView>(R.id.textView3).setOnClickListener {
            startActivity(Intent(this,CreateAccount::class.java))
            finish()
        }
        submit.setOnClickListener {
            if(phonenum.text.toString().isNotBlank() && pass.text.toString().isNotBlank()  ){
                login(phonenum.text.toString().trim(),pass.text.toString().trim())
            }else{
                Toast.makeText(this,"All fields are Required",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String,password:String) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...") // Set the message to display
        progressDialog.setCancelable(false) // Prevent users from dismissing the dialog by tapping outside
        progressDialog.show()
        if(email.trim() == "admin@admin.com" && password.trim() == ("password")){
            startActivity(Intent(this@Login,AdminPanel::class.java))
            finish()
        }
        val auth=Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Sign in success, update UI with the signed-in user's information
                    val sd=getSharedPreferences("groc", MODE_PRIVATE)
                    if(sd.getString("_id",null).isNullOrBlank()){
                        val edit=sd.edit()
                        edit.putString("_id",user?.uid.toString())
                        edit.putString("email",email)
                        edit.putString("name",password)
                        edit.putString("isSeller","no")
                        edit.commit()
                        edit.apply()
                    }
                    progressDialog.dismiss()
                    startActivity(Intent(this@Login,DashBoardActivity::class.java))
                    finish()

                } else {
                    progressDialog.dismiss()
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}