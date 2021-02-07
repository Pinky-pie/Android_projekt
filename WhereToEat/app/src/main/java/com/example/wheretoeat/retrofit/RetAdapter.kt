package com.example.wheretoeat.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.databases.restaurant.RestaurantViewModel
import com.example.wheretoeat.fragments.MainFragment
import com.squareup.picasso.Picasso


class RetAdapter(private val lista: MutableList<Restaurant>, private val listener: OnItemClickListener): RecyclerView.Adapter<RetAdapter.RetViewHolder>() {

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

    inner class RetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.restaurant_img)
        val location_name : TextView = itemView.findViewById<TextView>(R.id.loc_name)
        val location_address : TextView = itemView.findViewById<TextView>(R.id.address)
        val location_price : TextView = itemView.findViewById<TextView>(R.id.price)

        init{
            itemView.setOnClickListener(this)
            itemView.findViewById<ImageButton>(R.id.rec_fav).setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
            {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

//    fun add_toFav(rest: Restaurant)
//    {
//
//        val restaurant = com.example.wheretoeat.databases.restaurant.Restaurant(
//                rest.id,
//                rest.name,
//                rest.address,
//                rest.city,
//                rest.area,
//                rest.postal_code,
//                rest.country,
//                rest.phone,
//                rest.lat,
//                rest.lng,
//                rest.price,
//                rest.reserve_url,
//                rest.mobile_reserve_url,
//                rest.image_url,
//                rest.state,
//                true
//        )
////                Add new User to database
//        RestaurantViewModel.addRestaurant(restaurant)
////            Snackbar.make(this, "Successfully added to favourites!", Snackbar.LENGTH_SHORT).show()
//    }
}