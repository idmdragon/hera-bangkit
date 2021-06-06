package com.hera.bangkit.ui.auth.register.registerfragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentRegister2Binding
import java.util.*

class Register2Fragment : Fragment() {

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var fullName: String
    private lateinit var password: String
    private lateinit var phoneNumber: String

    private lateinit var binding: FragmentRegister2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegister2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_register2Fragment_to_register1Fragment)
        )

        val fireStore = FirebaseFirestore.getInstance()

        val bundle = arguments

        binding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }

        if (bundle != null) {
            email = Register2FragmentArgs.fromBundle(bundle).registerEmail
            fullName = Register2FragmentArgs.fromBundle(bundle).registerFullName
            username = Register2FragmentArgs.fromBundle(bundle).registerUsername
            password = Register2FragmentArgs.fromBundle(bundle).registerPassword
            phoneNumber = Register2FragmentArgs.fromBundle(bundle).registerPhoneNumber
        }

        with(binding) {

            btnLanjutkan.setOnClickListener {

                val placeOfBirth = binding.etPlaceOfBirth.text.toString().trim()
                val dateOfBirth = binding.etDateOfBirth.text.toString().trim()
                val address = binding.etAddress.text.toString().trim()
                val nik = binding.etNik.text.toString().trim()

                binding.etDateOfBirth.setOnClickListener {

                    val calendar = Calendar.getInstance()
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val month = calendar.get(Calendar.MONTH)
                    val year = calendar.get(Calendar.YEAR)

                    val datePicker = activity?.let { it ->
                        DatePickerDialog(
                            it,
                            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                                etDateOfBirth.setText(dayOfMonth.toString() + "-" + (month.toInt() + 1) + "-" + year)
                            },
                            year,
                            month,
                            day
                        )
                    }
                    datePicker!!.show()
                }


                if (placeOfBirth.isEmpty()) {
                    binding.PlaceOfBirthLayout.error = "Tempat Lahir tidak boleh kosong"
                } else if (dateOfBirth.isEmpty()) {
                    binding.DateOfBirthLayout.error = "Tanggal Lahir tidak boleh kosong"
                } else if (address.isEmpty()) {
                    binding.AddressLayout.error = "Alamat tidak boleh kosong"
                } else if (nik.isEmpty()) {
                    binding.NIKLayout.error = "NIK tidak boleh kosong"
                } else {
                    val toFragment2 = Register2FragmentDirections.actionRegister2FragmentToRegister3Fragment(email, fullName, username, password, phoneNumber,placeOfBirth, dateOfBirth, address, nik)
                    view.findNavController().navigate(toFragment2)
                }
            }

        }
    }
}