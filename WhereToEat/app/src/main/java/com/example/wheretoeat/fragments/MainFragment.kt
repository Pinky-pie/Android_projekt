package com.example.wheretoeat.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.databases.restaurant.RestaurantViewModel
import com.example.wheretoeat.databases.user.UserViewModel
import com.example.wheretoeat.retrofit.Communicator
import com.example.wheretoeat.retrofit.MainViewModel
import com.example.wheretoeat.retrofit.RetAdapter
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), RetAdapter.OnItemClickListener {

    private lateinit var mUserViewModel: UserViewModel
    private val retrofitViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val prof_but = view.findViewById<ImageButton>(R.id.profileButton)
        val rec = view.findViewById<RecyclerView>(R.id.recycler_view)

        mUserViewModel.readData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if(it.image?.size != null)
                {
                    prof_but.setImageBitmap(BitmapFactory.decodeByteArray(it.image, 0, it.image?.size!!))
                }
            }
        })


        retrofitViewModel.getAllRestaurantsFromDropBox()
        retrofitViewModel.apiRestaurants.observe(viewLifecycleOwner, {
            rec.adapter = RetAdapter(it,this)
            rec.layoutManager = LinearLayoutManager(this.context)
            rec.setHasFixedSize(true)
        })

        prof_but.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }
        return view
    }

    override fun onItemClick(position: Int) {
        retrofitViewModel.apiRestaurants.observe(viewLifecycleOwner, {
            Communicator.respond = it[position]
        })
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }

}

