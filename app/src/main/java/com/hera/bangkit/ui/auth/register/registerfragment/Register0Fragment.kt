package com.hera.bangkit.ui.auth.register.registerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister0Binding

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
//        binding.btnDaftar.setOnClickListener {
//            view.findNavController().navigate(R.id.action_register0Fragment_to_register1Fragment)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}