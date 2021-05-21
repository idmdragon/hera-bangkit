package com.hera.bangkit.ui.auth.register.registerfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister0Binding
import com.hera.bangkit.ui.auth.login.LoginActivity

class Register0Fragment : Fragment() {

    private var _binding: FragmentRegister0Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegister0Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnDaftar.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_register0Fragment_to_register1Fragment)
        )
        binding.tvMasuk.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}