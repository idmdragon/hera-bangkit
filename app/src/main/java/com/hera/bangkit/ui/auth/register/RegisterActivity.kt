package com.hera.bangkit.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.R
import com.hera.bangkit.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_regis_host) as NavHostFragment
        val navController = navHostFragment.navController

    }

    // baru, klo user dah pernah login, langsung aja ke main activity
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            Intent(this@RegisterActivity, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}