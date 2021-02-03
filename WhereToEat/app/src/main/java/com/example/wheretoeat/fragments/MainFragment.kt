package com.example.wheretoeat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.retrofit.MainViewModel
import com.example.wheretoeat.retrofit.MainViewModelFactory
import com.example.wheretoeat.retrofit.repository.Repository

class MainFragment : Fragment() {
//    private lateinit var retrofitViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val prof_but = view.findViewById<ImageButton>(R.id.profileButton)

//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//        retrofitViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//        retrofitViewModel.getPost()

//        retrofitViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
//            Log.d("Response", response.id.toString())
//            Log.d("Response", response.name)
//            Log.d("Response", response.address)
//            Log.d("Response", response.city)
//            Log.d("Response", response.state)
//            Log.d("Response", response.area)
//            Log.d("Response", response.postal_code.toString())
//            Log.d("Response", response.country)
//            Log.d("Response", response.phone)
//            Log.d("Response", response.lat.toString())
//            Log.d("Response", response.lng.toString())
//            Log.d("Response", response.price.toString())
//            Log.d("Response", response.reserve_url)
//            Log.d("Response", response.mobile_reserve_url)
//            Log.d("Response", response.image_url)
//
//        })

        prof_but.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }
        return view
    }
}