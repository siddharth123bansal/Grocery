package com.example.grocery.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Models.Fruits
import com.example.grocery.R

class BannerAdapter(val context: Context, val list:ArrayList<Fruits> ,val type:Int):RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image=itemView.findViewById<ImageView>(R.id.imageView4)
        val banner=itemView.findViewById<ImageView>(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       if(type==1){
           val view=LayoutInflater.from(context).inflate(R.layout.smple_second,parent,false)
           return BannerAdapter.ViewHolder(view)
       }else{
           val view=LayoutInflater.from(context).inflate(R.layout.banner_sample_layout,parent,false)
           return BannerAdapter.ViewHolder(view)
       }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val iamge=list.get(position)
        if(type==1){
            holder.image.setImageResource(iamge.image)
        }else{
            holder.banner.setImageResource(iamge.image)
        }
    }
}