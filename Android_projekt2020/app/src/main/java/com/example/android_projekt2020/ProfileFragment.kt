package com.example.android_projekt2020

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
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream

// but1 choose_img but2 upload_img  //Prof photo selected_img, Name person_name, Address person_address, Number person_number, Email person email, Favourites-->

class ProfileFragment : Fragment() {

    lateinit var sqliteHelper: DatabaseOperations




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        sqliteHelper = DatabaseOperations(this.context)
        print("We are in profile screen")

        val choose = view.findViewById<Button>(R.id.choose_img)
        val insert = view.findViewById<Button>(R.id.insert_data)
        val image = view.findViewById<ImageView>(R.id.selected_img)
        val name = view.findViewById<EditText>(R.id.person_name)
        val password = view.findViewById<EditText>(R.id.person_password)
        val address = view.findViewById<EditText>(R.id.person_address)
        val number = view.findViewById<EditText>(R.id.person_number)
        val email = view.findViewById<EditText>(R.id.person_email)

        choose.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ActivityCompat.checkSelfPermission(this.requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
//                    permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else
                {
//                    permission already granted
                    pickImageFromGallery()
                }
            }
            else
            {
//                system OS is < Marshmalow
                pickImageFromGallery()
            }
        }

        insert.setOnClickListener{
            if(image == null || name.length() == 0 || password.length() == 0 || address.length() == 0 || number.length() == 0 || email.length() == 0)
            {
                Snackbar.make(this.requireView(), "Please fill the fields correctly!", Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val img: ByteArray = imageViewToByte(image)
                sqliteHelper.insertData(img, name.toString(), password.toString(), address.toString(), number.toString(),email.toString())
            }
        }

        return view
    }

    private fun imageViewToByte(image:ImageView): ByteArray {
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
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
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