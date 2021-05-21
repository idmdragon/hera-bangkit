package com.hera.bangkit.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.hera.bangkit.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_regis_host) as NavHostFragment
        val navController = navHostFragment.navController


    }
}