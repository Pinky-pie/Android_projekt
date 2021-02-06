package com.example.wheretoeat.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.squareup.picasso.Picasso


class RetAdapter(private val lista: MutableList<Restaurant>): RecyclerView.Adapter<RetAdapter.RetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.restaurant_item,
            parent, false
        )
        return RetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RetViewHolder, position: Int) {
        val currentItem = lista[position]

        Picasso.get().load(currentItem.image_url).into(holder.imageView)

        holder.location_address.text = currentItem.address
        holder.location_name.text = currentItem.name
        holder.location_price.text = currentItem.price.toString()

    }

    override fun getItemCount() = lista.size



    class RetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.restaurant_img)
        val location_name : TextView = itemView.findViewById<TextView>(R.id.loc_name)
        val location_address : TextView = itemView.findViewById<TextView>(R.id.address)
        val location_price : TextView = itemView.findViewById<TextView>(R.id.price)
    }
}