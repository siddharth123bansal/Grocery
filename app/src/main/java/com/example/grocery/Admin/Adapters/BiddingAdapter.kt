package com.example.grocery.Admin.Adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Admin.Models.NewBidingModel
import com.example.grocery.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class BiddingAdapter(val context:Context, private val list:ArrayList<NewBidingModel>):RecyclerView.Adapter<BiddingAdapter.ViewHolder>() {


    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val bidingImage=itemview.findViewById<CircleImageView>(R.id.profile_image)
        val paintingName=itemview.findViewById<TextView>(R.id.sellerName)
        val bidingPrice=itemview.findViewById<TextView>(R.id.sellerContry)
        val partBtn=itemview.findViewById<Button>(R.id.acceptBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.biding_sample,parent,false)
        return BiddingAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bid=list.get(position)
        Picasso.get().load(bid.image).into(holder.bidingImage)
        holder.paintingName.text = bid.imageName
        holder.bidingPrice.text = bid.bidAmount.toString()
    }
}