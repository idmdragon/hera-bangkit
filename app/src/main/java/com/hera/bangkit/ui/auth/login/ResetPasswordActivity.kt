package com.hera.bangkit.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.databinding.ActivityResetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnKirim.setOnClickListener {
                val email = binding.etEmail.text.toString().trim()

                if(email.isEmpty()){
                    etEmail.error = "Email tidak boleh kosong"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.error = "Email tidak valid"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(this@ResetPasswordActivity, "Cek email untuk reset password", Toast.LENGTH_SHORT)
                            .show()
                        Intent(this@ResetPasswordActivity, LoginActivity::class.java).also {
                            it.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    } else {
                        Toast.makeText(this@ResetPasswordActivity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}