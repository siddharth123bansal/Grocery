package com.example.grocery.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Models.VegesModel
import com.example.grocery.R

class VegesAdapter(val contxt:Context,val list:ArrayList<VegesModel>):RecyclerView.Adapter<VegesAdapter.Veges>() {
    class Veges(itemView: View):RecyclerView.ViewHolder(itemView){
        val image=itemView.findViewById<ImageView>(R.id.vegesImage)
        val title=itemView.findViewById<TextView>(R.id.vegeTitle)
        val weight=itemView.findViewById<TextView>(R.id.vegeWeight)
        val price=itemView.findViewById<TextView>(R.id.vegePrice)
        val add=itemView.findViewById<ImageView>(R.id.vegeAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Veges {
        val view =LayoutInflater.from(contxt).inflate(R.layout.vegebackground,parent,false)
        return VegesAdapter.Veges(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Veges, position: Int) {
        val veges=list.get(position)
        holder.image.setImageResource(veges.image)
        holder.title.setText(veges.name)
        holder.weight.setText(veges.weight)
        holder.price.setText(veges.price)
    }
}