package com.hera.bangkit.ui.auth.register.registerfragment

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.app.Person.fromBundle
import androidx.media.AudioAttributesCompat.fromBundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister0Binding
import com.hera.bangkit.databinding.FragmentRegister1Binding
import org.w3c.dom.Text

class Register1Fragment : Fragment() {
    private lateinit var email: String
    private lateinit var binding: FragmentRegister1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegister1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnBack.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_register1Fragment_to_register0Fragment)
        )

        val fireStore = FirebaseFirestore.getInstance()

        val bundle = arguments

        binding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }

        // dari fragmen sebelumnya
        if (bundle != null) {
            email = Register1FragmentArgs.fromBundle(bundle).registerEmail
        }

        with(binding) {

            btnLanjutkan.setOnClickListener {

                val fullName = binding.etFullname.text.toString().trim()
                val username = binding.etUsername.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                val phoneNumber = binding.etPhoneNumber.text.toString().trim()

                if (fullName.isEmpty()) {
                    binding.FullNameLayout.error = "Nama Lengkap tidak boleh kosong"
                } else if (username.isEmpty()) {
                    binding.UsernameLayout.error = "Nama Pengguna tidak boleh kosong"
                } else if (password.isEmpty()) {
                    binding.PasswordLayout.error = "Password tidak boleh kosong"
                } else if (phoneNumber.isEmpty()) {
                    binding.PhoneNumberLayout.error = "Nomor Telepon tidak boleh kosong"
                } else {
                    val toFragment2 =
                        Register1FragmentDirections.actionRegister1FragmentToRegister2Fragment(
                            email,
                            fullName,
                            username,
                            password,
                            phoneNumber
                        )
                    view.findNavController().navigate(toFragment2)
                }
            }
        }
    }
}

