package com.hera.bangkit.ui.auth.register.registerfragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister3Binding
import com.hera.bangkit.utils.Constant

class Register3Fragment : Fragment() {

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var fullName: String
    private lateinit var password: String
    private lateinit var phoneNumber: String
    private lateinit var placeOfBirth: String
    private lateinit var dateOfBirth: String
    private lateinit var address: String
    private lateinit var nik: String

    private lateinit var binding: FragmentRegister3Binding

    var photoMax: Int = 1
    var photoLocation: Uri? = null
    lateinit var storageRef: StorageReference
    var profilePic: String = ""

    private fun getFileExtension(uri: Uri?): String? {
        val contentResolver = activity?.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver?.getType(uri!!))
    }

    private fun findPhoto() {
        val pic = Intent()
        pic.type = "image/*"
        pic.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(pic, photoMax)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == photoMax && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            photoLocation = data.data
            Glide.with(requireContext())
                .load(photoLocation)
                .into(binding.avatar)
            uploadImage()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegister3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_register3Fragment_to_register2Fragment)
        )

        val fireStore = FirebaseFirestore.getInstance()

        val bundle = arguments

        binding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }

        if (bundle != null) {
            email = Register3FragmentArgs.fromBundle(bundle).registerEmail
            fullName = Register3FragmentArgs.fromBundle(bundle).registerFullName
            username = Register3FragmentArgs.fromBundle(bundle).registerUsername
            password = Register3FragmentArgs.fromBundle(bundle).registerPassword
            phoneNumber = Register3FragmentArgs.fromBundle(bundle).registerPhoneNumber
            placeOfBirth = Register3FragmentArgs.fromBundle(bundle).registerPlaceOfBirth
            dateOfBirth = Register3FragmentArgs.fromBundle(bundle).registerDateOfBirth
            address = Register3FragmentArgs.fromBundle(bundle).registerAddress
            nik = Register3FragmentArgs.fromBundle(bundle).registerNIK
        }

        with(binding) {

            btnUploadProfil.setOnClickListener {
                // upload foto
                findPhoto()
                uploadImage()
            }

            btnLanjutkan.setOnClickListener {
                var avatarUri = if (profilePic.isNotEmpty()) profilePic else Constant.IMG
                val toFragment3 = Register3FragmentDirections.actionRegister3FragmentToRegister4Fragment(email, fullName, username, password, phoneNumber, placeOfBirth, dateOfBirth,address, nik, avatarUri)
                view.findNavController().navigate(toFragment3)
            }
        }
    }

    private fun uploadImage() {
        if (photoLocation != null) {
            storageRef = FirebaseStorage.getInstance().reference.child("AvatarPath")
            val storage = storageRef.child(
                System.currentTimeMillis()
                    .toString() + "." + getFileExtension(photoLocation)
            )
            photoLocation?.let { it1 ->
                storage.putFile(it1).addOnSuccessListener {
                    storage.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                        profilePic = uri.toString()
                    })
                }
            }
        }
    }
}