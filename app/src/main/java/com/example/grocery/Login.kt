package com.example.grocery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    lateinit var phonenum:EditText
    lateinit var submit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phonenum=findViewById(R.id.editTextPhone)
        submit=findViewById(R.id.button2)
        submit.setOnClickListener {
            if(phonenum.text.toString().isNotBlank() ||phonenum.text.toString().length==10 ){
                login(phonenum.text.toString().trim())
            }else{
                Toast.makeText(this,"Enter a valid number",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(phonenum: String) {
        val sd=getSharedPreferences("groc", MODE_PRIVATE)
        val edit=sd.edit()
        edit.putString("number",phonenum)
        edit.commit()
        startActivity(Intent(this@Login,DashBoardActivity::class.java))
        finish()

    }

    fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}