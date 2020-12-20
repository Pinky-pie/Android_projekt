package com.example.android_projekt2020.fragments.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_projekt2020.R
import com.example.android_projekt2020.data.User
import com.example.android_projekt2020.data.UserViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val new_user_but = view.findViewById<Button>(R.id.new_user)
        new_user_but.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_registerFragment)
        }
        return view
    }

}