package com.hera.bangkit.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.R
import com.hera.bangkit.ui.auth.login.LoginActivity
import com.hera.bangkit.ui.dashboard.DashboardActivity
import com.hera.bangkit.ui.main.MainActivity
import com.hera.bangkit.utils.SharedPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var handler : Handler
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = SharedPreferences(this)


        Handler(mainLooper).postDelayed({
            if(onBoardingFinished()){
                checkUser()
            }else{
                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }, 1000)


    }

    private fun checkUser() {
        val user = FirebaseAuth.getInstance().currentUser
        Handler(Looper.getMainLooper()).postDelayed({
            if (user != null) {
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            } else {
                if (isOpenMain()){
                    val intent =
                        Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    this.finish()
                }else{
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }, 1000)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = this.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun isOpenMain(): Boolean{
        val sharedPref = this.getSharedPreferences("openMain", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}