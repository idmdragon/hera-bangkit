package com.hera.bangkit.ui.main.profile.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.source.local.entity.UserEntity
import com.hera.bangkit.data.source.remote.response.UserResponse
import com.hera.bangkit.databinding.ActivitySettingAccountBinding
import com.hera.bangkit.ui.auth.register.RegisterActivity
import com.hera.bangkit.ui.main.post.report.ReportViewModel
import com.hera.bangkit.ui.main.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SettingAccountActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingAccountBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: ProfileViewModel by viewModels()

    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }

            btnSimpan.setOnClickListener {

                val uid = firebaseAuth.currentUser?.uid
                uid?.let { viewModel.getUser(it).observe(this@SettingAccountActivity , { user ->

                    val address = etAddress.text.toString()
                    val dateOfBirth = etDateOfBirth.text.toString()
                    val email = etEmail.text.toString()
                    val fullName = etFullname.text.toString()
                    val guardianName1 = etGuardianName1.text.toString()
                    val guardianName2 = etGuardianName2.text.toString()
                    val guardianPhoneNumber1 = etGuardianPhone1.text.toString()
                    val guardianPhoneNumber2 = etGuardianPhone2.text.toString()
                    val nik = etNik.text.toString()
                    val phoneNumber = etPhoneNumber.text.toString()
                    val placeOfBirth = etPlaceOfBirth.text.toString()
                    val username = etUsername.text.toString()

//                    val user = UserEntity(
//                        address,
//                        dateOfBirth,
//                        email,
//                        fullName,
//                        guardianName1,
//                        guardianName2,
//                        guardianPhoneNumber1,
//                        guardianPhoneNumber2,
//                        nik,
//                        phoneNumber,
//                        placeOfBirth,
//                        username)
//
//                    saveUser(user)
                })

        }
    }
        }
    }

    private fun saveUser(user: UserResponse) = CoroutineScope(Dispatchers.IO).launch {
        try {
            userCollectionRef.add(user).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@SettingAccountActivity, "Successfully saved data.", Toast.LENGTH_LONG).show()
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@SettingAccountActivity , e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
