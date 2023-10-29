package com.example.grocery.Admin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Admin.Models.UserData
import com.example.grocery.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SellersAdapter(val contxt: Context,val list:ArrayList<UserData>):RecyclerView.Adapter<SellersAdapter.ViewHolder>() {


    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val image=itemview.findViewById<CircleImageView>(R.id.profile_image)
        val username=itemview.findViewById<TextView>(R.id.sellerName)
        val country=itemview.findViewById<TextView>(R.id.sellerContry)
        val viewbtn=itemview.findViewById<Button>(R.id.viewBtn)
        val accptbtn=itemview.findViewById<Button>(R.id.acceptBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(contxt).inflate(R.layout.sellers_layout,parent,false)
        return SellersAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val seller=list.get(position)
        holder.username.setText(seller.username)
        holder.country.setText(seller.usercountry)
    }
}