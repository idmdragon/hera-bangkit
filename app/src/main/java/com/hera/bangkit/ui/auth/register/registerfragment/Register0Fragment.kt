package com.hera.bangkit.ui.auth.register.registerfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.databinding.FragmentRegister0Binding
import com.hera.bangkit.ui.auth.login.LoginActivity
import com.hera.bangkit.ui.main.MainActivity

class Register0Fragment : Fragment() {

    private lateinit var binding: FragmentRegister0Binding

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegister0Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onBoardingFinish()
        with(binding) {

            btnDaftar.setOnClickListener {

                val email = binding.etEmail.text.toString().trim()
                if (email.isEmpty()) {
                    binding.EmailLayout.error = "Email Tidak boleh kosong"
                } else {
                    val toFragment0 = Register0FragmentDirections.actionRegister0FragmentToRegister1Fragment(email)
                    view.findNavController().navigate(toFragment0)
                }
            }

            tvMasuk.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        }
    }

    private fun onBoardingFinish() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}