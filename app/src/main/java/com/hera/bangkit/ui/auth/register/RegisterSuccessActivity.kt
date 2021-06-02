package com.hera.bangkit.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.hera.bangkit.databinding.ActivityRegisterSuccessBinding
import com.hera.bangkit.ui.auth.register.registerfragment.Register4FragmentArgs
import com.hera.bangkit.ui.main.MainActivity
import com.hera.bangkit.utils.Constant

class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /////cek lagi besok
        with(binding) {

            btnRegisterSuccess.setOnClickListener {
                var user = binding.tvPengguna.text.toString().trim()

                if (user.isEmpty()) {
                    binding.tvPengguna.setText("Sahabat HERA")
                } else

//        binding.btnRegisterSuccess.setOnClickListener {
                startActivity(Intent(this@RegisterSuccessActivity, MainActivity::class.java))
            }

        }
    }
}