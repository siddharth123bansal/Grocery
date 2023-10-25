package com.example.grocery.Fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.grocery.Helpers.BecomeSeller
import com.example.grocery.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetShow(context: Context) : BottomSheetDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet_show)
        findViewById<TextView>(R.id.becomeSellerOption)?.setOnClickListener {
            val intent=Intent(context,BecomeSeller::class.java)
            context.startActivity(intent)
        }
    }
}