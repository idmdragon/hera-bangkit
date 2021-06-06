package com.hera.bangkit.ui.main.profile.setting

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivitySettingBinding
import com.hera.bangkit.ui.auth.login.LoginActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnBack.setOnClickListener {
                finish()
            }

            btnLogout.setOnClickListener {
                var dialog = AlertDialog.Builder(this@SettingActivity)
                dialog.setTitle("Log out")
                dialog.setMessage("Apakah kamu yakin ingin keluar?")
                dialog.setPositiveButton("Iya") { dialog: DialogInterface?, which: Int ->
                    firebaseAuth.signOut()
                    startActivity(Intent(this@SettingActivity, LoginActivity::class.java)).also {
                        finish()
                    }
                }
                dialog.setNegativeButton("Tidak") { dialog: DialogInterface?, which: Int -> }
                dialog.show()
                }
            }

        }

// KLO MAU CUSTOM
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
//    }

    }
