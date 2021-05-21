package com.hera.bangkit.ui.auth.register.registerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister0Binding
import com.hera.bangkit.databinding.FragmentRegister1Binding

class Register1Fragment : Fragment() {
    private var _binding: FragmentRegister1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegister1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLanjutkan.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_register1Fragment_to_register2Fragment)
        )
        binding.btnBack.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_register1Fragment_to_register0Fragment)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}