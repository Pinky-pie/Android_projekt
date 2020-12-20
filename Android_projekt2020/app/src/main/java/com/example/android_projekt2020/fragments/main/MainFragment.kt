package com.example.android_projekt2020.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.android_projekt2020.fragments.profile.ProfileFragment
import com.example.android_projekt2020.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val prof_but = view.findViewById<ImageButton>(R.id.profileButton)
        prof_but.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }

        return view
    }
}