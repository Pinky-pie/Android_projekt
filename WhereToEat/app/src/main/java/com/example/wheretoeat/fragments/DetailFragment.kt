package com.example.wheretoeat.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databases.restaurant.RestaurantViewModel
import com.example.wheretoeat.databases.user.User
import com.example.wheretoeat.databases.user.UserViewModel
import com.example.wheretoeat.retrofit.Communicator
import com.example.wheretoeat.retrofit.Restaurant
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class DetailFragment : Fragment() {

    private lateinit var mRestaurantViewModel: RestaurantViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        mRestaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val pic = view.findViewById<ImageView>(R.id.rest_img)
        val fav = view.findViewById<ImageButton>(R.id.add_fav)
        val add_pic = view.findViewById<ImageButton>(R.id.add_photo)
        val loca = view.findViewById<ImageButton>(R.id.loc)
        val call = view.findViewById<ImageButton>(R.id.call)
        val name = view.findViewById<TextView>(R.id.d_name)
        val address = view.findViewById<TextView>(R.id.d_address)
        val city = view.findViewById<TextView>(R.id.d_city)
        val country = view.findViewById<TextView>(R.id.d_country)
        val price = view.findViewById<TextView>(R.id.d_price)

        val c = Communicator.respond

        name.text = c.name
        address.text = c.address
        city.text = c.city
        country.text = c.country
        price.text = c.price.toString()
        Picasso.get().load(c.image_url).into(pic)

        loca.setOnClickListener{
            // Create a Uri from an intent string. Use the result to create an Intent.
            val gmmIntentUri = Uri.parse("google.streetview:cbll=" + c.lat +","+c.lng)

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps")

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent)
        }

        call.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + c.phone)
            startActivity(dialIntent)
        }

        fav.setOnClickListener{
//                Create user
            val restaurant = com.example.wheretoeat.databases.restaurant.Restaurant(
                    c.id,
                    c.name,
                    c.address,
                    c.city,
                    c.area,
                    c.postal_code,
                    c.country,
                    c.phone,
                    c.lat,
                    c.lng,
                    c.price,
                    c.reserve_url,
                    c.mobile_reserve_url,
                    c.image_url,
                    c.state,
                    true
                    )
//                Add new User to database
            mRestaurantViewModel.addRestaurant(restaurant)
            Snackbar.make(this.requireView(), "Successfully added to favourites!", Snackbar.LENGTH_SHORT).show()
        }

        view.findViewById<ImageButton>(R.id.homeButton).setOnClickListener{
            findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
        }

        return view
    }

}