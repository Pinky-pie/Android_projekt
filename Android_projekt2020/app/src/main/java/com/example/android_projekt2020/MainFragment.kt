package com.example.android_projekt2020

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val prof_but = view.findViewById<ImageButton>(R.id.profileButton)
        prof_but.setOnClickListener{
                val newFragment = ProfileFragment()
                val fragmentManager = fragmentManager
                fragmentManager!!.beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit()
        }

        return view
    }
}