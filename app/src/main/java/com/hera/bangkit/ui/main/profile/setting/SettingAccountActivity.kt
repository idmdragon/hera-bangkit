package com.hera.bangkit.ui.main.profile.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@AndroidEntryPoint
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
            val uid = firebaseAuth.currentUser?.uid
            uid?.let { viewModel.getUser(it).observe(this@SettingAccountActivity , { user ->

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



                        val newUser = UserEntity(
                            address,
                            item.avatar,
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
                            username)
                Log.d("SettingAccount","Isi User $newUser")
//                    saveUser(user)

                    }
                }
            })
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
