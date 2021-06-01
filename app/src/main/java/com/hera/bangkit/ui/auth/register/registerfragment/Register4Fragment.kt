package com.hera.bangkit.ui.auth.register.registerfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hera.bangkit.data.response.UserEntity
import com.hera.bangkit.databinding.FragmentRegister4Binding
import com.hera.bangkit.ui.auth.register.RegisterSuccessActivity
import com.hera.bangkit.ui.auth.register.RegisterViewModel
import com.hera.bangkit.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register4Fragment : Fragment() {

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var fullName: String
    private lateinit var password: String
    private lateinit var phoneNumber: String
    private lateinit var placeOfBirth: String
    private lateinit var dateOfBirth: String
    private lateinit var address: String
    private lateinit var nik: String
    private lateinit var avatar: String

    private lateinit var binding: FragmentRegister4Binding
    private val viewModel : RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegister4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val fireStore = FirebaseFirestore.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()

        val bundle = arguments

        binding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }

        if (bundle != null) {
            email = Register4FragmentArgs.fromBundle(bundle).registerEmail
            fullName = Register4FragmentArgs.fromBundle(bundle).registerFullName
            username = Register4FragmentArgs.fromBundle(bundle).registerUsername
            password = Register4FragmentArgs.fromBundle(bundle).registerPassword
            phoneNumber = Register4FragmentArgs.fromBundle(bundle).registerPhoneNumber
            placeOfBirth = Register4FragmentArgs.fromBundle(bundle).registerPlaceOfBirth
            dateOfBirth = Register4FragmentArgs.fromBundle(bundle).registerDateOfBirth
            address = Register4FragmentArgs.fromBundle(bundle).registerAddress
            nik = Register4FragmentArgs.fromBundle(bundle).registerNIK
            avatar = Register4FragmentArgs.fromBundle(bundle).registerAvatar
        }

        with(binding) {

            btnSelesai.setOnClickListener {
                val guardianName1 = binding.etGuardianName1.text.toString().trim()
                val guardianName2 = binding.etGuardianName2.text.toString().trim()
                val guardianPhoneNumber1 = binding.etGuardianPhone1.text.toString().trim()
                val guardianPhoneNumber2 = binding.etGuardianPhone2.text.toString().trim()

                if (guardianName1.isEmpty()) {
                    binding.GuardianName1Layout.error = "Nama Guardian 1 tidak boleh kosong"
                } else if (guardianName2.isEmpty()) {
                    binding.GuardianName2Layout.error = "Nama Guardian 2 tidak boleh kosong"
                } else if (guardianPhoneNumber1.isEmpty()) {
                    binding.GuardianPhone1Layout.error =
                        "Nomor Telepon Guardian 1 tidak boleh kosong"
                } else if (guardianPhoneNumber2.isEmpty()) {
                    binding.GuardianPhone2Layout.error =
                        "Nomor Telepon Guardian 2 tidak boleh kosong"
                } else {

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(requireContext(), "User Created", Toast.LENGTH_SHORT)
                                    .show()

//                            taruh di Firestore
                                val uid = firebaseAuth.currentUser?.uid
                                val user = UserEntity(
                                    address,
                                    avatar,
                                    dateOfBirth,
                                    email,
                                    fullName,
                                    guardianName1,
                                    guardianName2,
                                    guardianPhoneNumber1,
                                    guardianPhoneNumber2,
                                    nik,
                                    phoneNumber,
                                    placeOfBirth,
                                    uid!!,
                                    username
                                )

                                viewModel.insertUser(user)

                                Toast.makeText(requireContext(),"User Created", Toast.LENGTH_SHORT).show()
                                val intent = Intent(activity, RegisterSuccessActivity::class.java)
                                startActivity(intent)
                            }
                        }
                }
            }
        }
    }
}