package com.hera.bangkit.ui.main.profile.setting

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivitySettingBinding
import com.hera.bangkit.ui.auth.login.LoginActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }

            btnLanguange.setOnClickListener {
                val local = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(local)
            }

            btnLogout.setOnClickListener {
                val dialog = AlertDialog.Builder(this@SettingActivity)
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

            btnAkun.setOnClickListener {
                startActivity(Intent(this@SettingActivity, SettingAccountActivity::class.java))
            }

            btnAbout.setOnClickListener {
                startActivity(Intent(this@SettingActivity, SettingAboutActivity::class.java))
            }
        }
    }
}