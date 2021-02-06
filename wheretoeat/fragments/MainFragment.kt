package com.example.wheretoeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.retrofit.MainViewModel
import com.example.wheretoeat.retrofit.RetAdapter

class MainFragment : Fragment() {
    private val retrofitViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val prof_but = view.findViewById<ImageButton>(R.id.profileButton)
        val rec = view.findViewById<RecyclerView>(R.id.recycler_view)

        retrofitViewModel.getAllRestaurantsFromDropBox()
        retrofitViewModel.apiRestaurants.observe(viewLifecycleOwner, {
            rec.adapter = RetAdapter(it)
            rec.layoutManager = LinearLayoutManager(this.context)
            rec.setHasFixedSize(true)
        })

        prof_but.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }
        return view
    }
}