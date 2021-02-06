package com.example.wheretoeat.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databases.user.UserViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream

class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        val name = view.findViewById<EditText>(R.id.name)
        val address = view.findViewById<EditText>(R.id.address)
        val email = view.findViewById<EditText>(R.id.email)
        val ph_nr = view.findViewById<EditText>(R.id.number)
        val but = view.findViewById<Button>(R.id.update)
        val photo = view.findViewById<ImageView>(R.id.selected_img)
        val choose = view.findViewById<Button>(R.id.choose_img)
        val hbut = view.findViewById<ImageButton>(R.id.homeButto)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.readData().observe(viewLifecycleOwner, Observer {
            if(it != null)
            {
                name.setText(it.name.toString())
                address.setText(it.address.toString())
                email.setText(it.email.toString())
                ph_nr.setText(it.phone.toString())
                photo.setImageBitmap(BitmapFactory.decodeByteArray(it.image, 0, it.image?.size!!))
            }
        })

        but.setOnClickListener{
            val img: ByteArray = imageViewToByte(photo)
            mUserViewModel.updateData(name.text.toString(), address.text.toString(), email.text.toString(), ph_nr.text.toString(), img)
            Snackbar.make(this.requireView(), "Data successfully updated!", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_profileFragment)
        }

        choose.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ActivityCompat.checkSelfPermission(this.requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
//                    permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    show popup to request runtime permission
                    requestPermissions(permissions, UpdateFragment.PERMISSION_CODE)
                }
                else
                {
//                    permission already granted
                    pickImageFromGallery()
                }
            }
            else
            {
//                system OS is < Marshmallows
                pickImageFromGallery()
            }
        }

        hbut.setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        return view
    }

    private fun imageViewToByte(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object{
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else
                {
                    //permission from popup denied
                    Snackbar.make(this.requireView(), "Permission denied!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val image = view?.findViewById<ImageView>(R.id.selected_img)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image?.setImageURI(data?.data)
        }
    }

}