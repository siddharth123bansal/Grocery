package com.example.grocery.Adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.Models.CartModel
import com.example.grocery.Models.CartsModel
import com.example.grocery.R
class CartAdapter(val context: Context, val list: ArrayList<CartsModel>, btn:Button):RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val image=itemview.findViewById<ImageView>(R.id.cartImage)
        val add=itemview.findViewById<ImageView>(R.id.qAdd)
        val minus=itemview.findViewById<ImageView>(R.id.qMinus)
        val title=itemview.findViewById<TextView>(R.id.cartTitle)
        val weight=itemview.findViewById<TextView>(R.id.cartWeight)
        val quantity=itemview.findViewById<TextView>(R.id.cartQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.cart_layout,parent,false)
        return CartAdapter.ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart=list.get(position)
        Log.d("cartItems",list.size.toString())
        holder.image.setImageResource(cart.image)
        holder.title.setText(cart.title)
        holder.weight.setText(cart.price.toString())
        holder.quantity.setText(cart.quantity.toString())
        holder.add.setOnClickListener {
            var item=Integer.parseInt(holder.quantity.text.toString())
            if(item<=5) {
                holder.quantity.setText((++item).toString())
            }
            else Toast.makeText(context,"At max 5 can be ordered",Toast.LENGTH_SHORT).show()
        }
        holder.minus.setOnClickListener {
            var item=Integer.parseInt(holder.quantity.text.toString())
            if(item>1) {
                holder.quantity.setText((--item).toString())
            }
            else Toast.makeText(context,"At Least 1 item needed to be ordered",Toast.LENGTH_SHORT).show()
        }
    }
}