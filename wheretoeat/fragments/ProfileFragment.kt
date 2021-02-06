package com.example.wheretoeat.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databases.user.UserViewModel


class ProfileFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val new_user_but = view.findViewById<Button>(R.id.new_user)
        val update_user_data_but = view.findViewById<Button>(R.id.update_user)
        val uname = view.findViewById<TextView>(R.id.name)
        val uaddress = view.findViewById<TextView>(R.id.adress)
        val uemail = view.findViewById<TextView>(R.id.email)
        val uphone = view.findViewById<TextView>(R.id.phone_number)
        val uimage = view.findViewById<ImageView>(R.id.prof_picture)
        val hbut = view.findViewById<ImageButton>(R.id.homeBut)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.readData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                uname.text = it.name.toString()
                uaddress.text = it.address.toString()
                uemail.text = it.email.toString()
                uphone.text = it.phone.toString()
                if(it.image?.size != null)
                {
                    uimage.setImageBitmap(BitmapFactory.decodeByteArray(it.image, 0, it.image?.size!!))
                }
            }
        })



        new_user_but.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_registerFragment)
        }

        update_user_data_but.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_updateFragment)
        }

        hbut.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_mainFragment)
        }

        return view
    }


}