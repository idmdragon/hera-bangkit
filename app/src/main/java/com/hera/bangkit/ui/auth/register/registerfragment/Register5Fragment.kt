package com.hera.bangkit.ui.auth.register.registerfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister2Binding
import com.hera.bangkit.databinding.FragmentRegister5Binding
import com.hera.bangkit.ui.auth.register.RegisterSuccessActivity
import com.hera.bangkit.ui.main.MainActivity

class Register5Fragment : Fragment() {
    private var _binding: FragmentRegister5Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegister5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSelesai.setOnClickListener {
            startActivity(Intent(requireContext(), RegisterSuccessActivity::class.java))
        }
        binding.btnBack.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_register5Fragment_to_register4Fragment)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

