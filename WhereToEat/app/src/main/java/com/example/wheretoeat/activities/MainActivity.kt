package com.example.wheretoeat.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.fragments.DetailFragment
import com.example.wheretoeat.retrofit.Communicator
import com.example.wheretoeat.retrofit.Restaurant

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragment_container))
        supportActionBar?.hide()
    }
}