package com.hera.bangkit.ui.auth.register.registerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister2Binding
import com.hera.bangkit.databinding.FragmentRegister3Binding

class Register3Fragment : Fragment() {
    private var _binding: FragmentRegister3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegister3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLanjutkan.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_register3Fragment_to_register4Fragment)
        )
        binding.btnBack.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_register3Fragment_to_register2Fragment)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}