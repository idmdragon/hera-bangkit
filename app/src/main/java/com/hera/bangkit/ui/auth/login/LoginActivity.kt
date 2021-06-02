package com.hera.bangkit.ui.auth.login

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.os.Build.ID
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityLoginBinding
import com.hera.bangkit.ui.auth.register.RegisterActivity
import com.hera.bangkit.ui.auth.register.registerfragment.Register1FragmentDirections
import com.hera.bangkit.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

//    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fireStore = FirebaseFirestore.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()

        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        with(binding) {

            btnMasuk.setOnClickListener {

                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                if (email.isEmpty()) {
                    binding.EmailLayout.error = "Email tidak boleh kosong"
                } else if (password.isEmpty()) {
                    binding.PasswordLayout.error = "Password tidak boleh kosong"
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this@LoginActivity, "User Berhasil Masuk", Toast.LENGTH_SHORT).show()
                            checkUserId(it.getResult()!!.user!!.uid)
                        } else {
                            Toast.makeText(this@LoginActivity, "Email/Password Salah", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }
    private fun checkUserId(uid: String) {
        val fireStore = FirebaseFirestore.getInstance()
        val df = fireStore.collection("User").document(uid)

        df.get().addOnSuccessListener {
            if (it.getString("User") == "1") {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Akun tidak ada", Toast.LENGTH_SHORT).show()
        }
    }


    fun register(view: View) {
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}


//        binding.btnMasuk.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//        }