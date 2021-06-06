package com.hera.bangkit.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hera.bangkit.R
import com.hera.bangkit.ui.auth.login.LoginActivity
import com.hera.bangkit.ui.dashboard.DashboardActivity
import com.hera.bangkit.utils.SharedPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var handler : Handler
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = SharedPreferences(this)

        handler = Handler(mainLooper)
        handler.postDelayed({
            var i = Intent()

            if(sharedPreferences.firstInstall == false){
                i = Intent(this, DashboardActivity::class.java)
            } else {
                i = Intent(this, LoginActivity::class.java)
            }

            startActivity(i)
            finish()
        }, 2000)
    }
}