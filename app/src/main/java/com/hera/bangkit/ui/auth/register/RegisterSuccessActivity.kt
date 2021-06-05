package com.hera.bangkit.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.data.response.UserResponse
import com.hera.bangkit.data.source.remote.RemoteResponse
import com.hera.bangkit.databinding.ActivityRegisterSuccessBinding
import com.hera.bangkit.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccessBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uid = firebaseAuth.currentUser?.uid
        uid?.let { viewModel.getUser(it).observe(this, ::getUserInfo) }

        with(binding) {

            btnRegisterSuccess.setOnClickListener {
                startActivity(Intent(this@RegisterSuccessActivity, MainActivity::class.java))
            }
        }

    }

    private fun getUserInfo(remoteResponse: RemoteResponse<UserResponse>) {
        remoteResponse.body.let {
            with(binding) {
                tvPengguna.text = it.username
            }
        }
    }
}