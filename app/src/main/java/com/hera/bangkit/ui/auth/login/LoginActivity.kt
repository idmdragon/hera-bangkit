package com.hera.bangkit.ui.auth.login

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.os.Build.ID
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import com.google.android.material.textfield.TextInputLayout
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityLoginBinding
import com.hera.bangkit.ui.auth.register.RegisterActivity
import com.hera.bangkit.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

//    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.btnMasuk.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }


// nakam canteen
        val loginEmail = findViewById<TextInputLayout>(R.id.otf_email)
        val loginPassword = findViewById<TextInputLayout>(R.id.otf_password)
        val btnMasuk = findViewById<Button>(R.id.btn_masuk)
        val loginLupaPassword = findViewById<TextView>(R.id.tv_login_lupa_password)

//        val firebaseAuth = FirebaseAuth.getInstance()
//
//        if (firebaseAuth.currentUser != null) {
//            val uid = firebaseAuth.currentUser!!.uid.toString()
//            // tar ganti jadi cek uid aja


        btnMasuk.setOnClickListener {
            val email = loginEmail.editText.toString().trim()
            val password = loginPassword.editText.toString().trim()

            if (TextUtils.isEmpty(email)) {
                loginEmail.setError("Email is required")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                loginPassword.setError("Password is required")
                return@setOnClickListener
            }
            if (password.length < 6) {
                loginPassword.setError("Password must be more than 6 characters")
                return@setOnClickListener
            }

//            //Database
//            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
////                    checkUserAccesLevel(it.getResult()!!.user!!.uid)
//                } else {
//                    Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
        }
    }
}

// ini
//    private fun checkUserAccesLevel(uid: String) {
//        val fireStore = FirebaseFirestore.getInstance()
//        val df = fireStore.collection("User").document(uid)
//
//        df.get().addOnSuccessListener {
//            if (it.getString("isAdmin") == "1") {
//                startActivity(Intent(applicationContext, AdminBeranda::class.java))
//            } else if (it.getString("isUser") == "1") {
//                startActivity(Intent(applicationContext, MahasiswaBeranda::class.java))
//            }
//        }.addOnFailureListener {
//            Toast.makeText(this, "Akun tidak ada", Toast.LENGTH_SHORT).show()
//        }
//    }

//
//        fun register(view: View) {
//            val intent = Intent(applicationContext, RegisterActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
//}

/////////////////////// kharisma rizki
//        firebaseAuth = FirebaseAuth.getInstance()
//    }
//
//    private fun login() {
//        binding.btnMasuk.visibility = View.INVISIBLE
//        val loginEmail = binding.otfEmail.editText.toString().trim()
//        val loginPassword = binding.otfPassword.editText.toString()
//
//        firebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // log in sukses
//                        Log.d(TAG, "login with email : success")
//                        val user = firebaseAuth.currentUser
//                        val name = user?.email
//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        intent.putExtra("EXTRA_NAME", name)
//                        startActivity(intent)
//                        finish()
//                    } else {
//                        // jika gagal log in, muncul pesan
//
//                        Log.w(TAG, "login with email : failed")
//                        binding.btnMasuk.visibility = View.VISIBLE
//                        Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//    }
//}


// ytb
//        loginLupaPassword.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Lupa Password")
//            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
//            builder.setView(view)
//            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
//                forgotPassword(loginEmail)
//            })
//            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->
//            })
//            builder.show()
//        }
//    }
//
//    private fun forgotPassword(loginEmail: TextInputLayout){
//        val firebaseAuth = FirebaseAuth.getInstance()
//        if(loginEmail.editText.toString().trim().isEmpty()){
//            return
//        }
//        if(!Pattern.EMAIL_ADDRESS.matcher(loginEmail.editText.toString().trim()).matches()){
//            return
//        }
//
//        firebaseAuth.sendPasswordResetEmail(loginEmail.editText.toString().trim())
//                .addOnCompleteListener{ task ->
//                    if(task.isSuccessful){
//                        Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
//                    }
//                    }
//        }
//
//


// baru

//    class LoginRegisterFragment : Fragment() {
//        private var ivSmallLogo: ImageView? = null
//        private var tvLoginMasuk: TextView? = null
//        private var tvLoginMasukText: TextView? = null
//        private var otfEmail: TextInputLayout? = null
//        private var otfPassword: TextInputLayout? = null
//        private var tvLoginLupaPassword: TextView? = null
//        private var btnMasuk: Button? = null
//        private var tvBelumPunya: TextView? = null
//        private var tvDaftar: TextView? = null
//        private var loginViewModel: LoginViewModel? = null
//
//        fun onCreate(@Nullable savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
//            loginViewModel.getUserLiveData()
//                .observe(this, object : Observer<FirebaseUser?>() {
//                    fun onChanged(firebaseUser: FirebaseUser?) {
//                        if (firebaseUser != null) {
//                            Navigation.findNavController(getView())
//                                .navigate(R.id.action_loginRegisterFragment_to_loggedInFragment)
//                        }
//                    }
//                })
//        }
//
//        @Nullable
//        fun onCreateView(
//            inflater: LayoutInflater,
//            @Nullable container: ViewGroup?,
//            @Nullable savedInstanceState: Bundle?
//        ): View {
//            val view: View = inflater.inflate(R.layout.fragment_loginregister, container, false)
//            emailEditText = view.findViewById(R.id.fragment_loginregister_email)
//            passwordEditText = view.findViewById(R.id.fragment_loginregister_password)
//            loginButton = view.findViewById(R.id.fragment_loginregister_login)
//            registerButton = view.findViewById(R.id.fragment_loginregister_register)
//            loginButton.setOnClickListener(object : OnClickListener() {
//                fun onClick(view: View?) {
//                    val email = emailEditText!!.text.toString()
//                    val password = passwordEditText!!.text.toString()
//                    if (email.length > 0 && password.length > 0) {
//                        loginRegisterViewModel.login(email, password)
//                    } else {
//                        Toast.makeText(
//                            getContext(),
//                            "Email Address and Password Must Be Entered",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            })
//            btnMasuk?.setOnClickListener(object : View.OnClickListener() {
//                fun onClick(view: View?) {
//                    val email = otfEmail!!.editText.toString()
//                    val password = otfPassword!!.editText.toString()
//                    if (email.length > 0 && password.length > 0) {
//                        loginViewModel?.login(email, password)
//                    } else {
//                        Toast.makeText(this(), "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            })
//            return view
//        }
//    }
//}