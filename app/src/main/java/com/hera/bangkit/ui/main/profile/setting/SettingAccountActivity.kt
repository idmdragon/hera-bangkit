package com.hera.bangkit.ui.main.profile.setting

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hera.bangkit.data.source.local.entity.UserEntity
import com.hera.bangkit.data.source.remote.response.UserResponse
import com.hera.bangkit.databinding.ActivitySettingAccountBinding
import com.hera.bangkit.ui.auth.register.RegisterActivity
import com.hera.bangkit.ui.main.post.report.ReportViewModel
import com.hera.bangkit.ui.main.profile.ProfileViewModel
import com.hera.bangkit.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SettingAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingAccountBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: ProfileViewModel by viewModels()


    var photoMax: Int = 1
    var photoLocation: Uri? = null
    lateinit var storageRef: StorageReference
    var profilePic: String = ""

    private fun getFileExtension(uri: Uri?): String? {
        val contentResolver = this.contentResolver
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
            Glide.with(this@SettingAccountActivity)
                .load(photoLocation)
                .into(binding.avatar)
            uploadImage()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }
            btnUploadProfil.setOnClickListener {
                // upload foto
                findPhoto()
                uploadImage()
            }

            val uid = firebaseAuth.currentUser?.uid
            uid?.let {
                viewModel.getUser(it).observe(this@SettingAccountActivity, { user ->

                    user.body.let { item ->
                        AddressLayout.editText?.setText(item.address)
                        DateOfBirthLayout.editText?.setText(item.dateOfBirth)
                        FullNameLayout.editText?.setText(item.fullName)
                        UsernameLayout.editText?.setText(item.username)
                        PhoneNumberLayout.editText?.setText(item.phoneNumber)
                        PlaceOfBirthLayout.editText?.setText(item.placeOfBirth)
                        NIKLayout.editText?.setText(item.nik)
                        GuardianName1Layout.editText?.setText(item.guardianName1)
                        GuardianPhone1Layout.editText?.setText(item.guardianPhoneNumber1)
                        GuardianName2Layout.editText?.setText(item.guardianName2)
                        GuardianPhone2Layout.editText?.setText(item.guardianPhoneNumber2)

                        btnSimpan.setOnClickListener {
                            val avatarUri = if (profilePic.isNotEmpty()) profilePic else Constant.IMG
                            val address = etAddress.text.toString()
                            val dateOfBirth = etDateOfBirth.text.toString()
                            val fullName = etFullname.text.toString()
                            val guardianName1 = etGuardianName1.text.toString()
                            val guardianName2 = etGuardianName2.text.toString()
                            val guardianPhoneNumber1 = etGuardianPhone1.text.toString()
                            val guardianPhoneNumber2 = etGuardianPhone2.text.toString()
                            val nik = etNik.text.toString()
                            val phoneNumber = etPhoneNumber.text.toString()
                            val placeOfBirth = etPlaceOfBirth.text.toString()
                            val username = etUsername.text.toString()

                            Log.d("SettingAccount","isi avatarURi $avatarUri")

                            val newUser = UserEntity(
                                address,
                                avatarUri,
                                dateOfBirth,
                                item.email,
                                fullName,
                                guardianName1,
                                guardianName2,
                                guardianPhoneNumber1,
                                guardianPhoneNumber2,
                                nik,
                                phoneNumber,
                                placeOfBirth,
                                uid,
                                username
                            )
                            Log.d("SettingAccount","isi newUser $newUser")
                            viewModel.updateUser(newUser)

                            Toast.makeText(this@SettingAccountActivity,"Perubahan berhasil disimpan",Toast.LENGTH_LONG).show()
                            Toast.makeText(this@SettingAccountActivity,"Perubahan mungkin membutuhkan waktu",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                })
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
