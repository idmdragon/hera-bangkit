//package com.hera.bangkit.ui.main.post.story
//import android.app.Activity
//import android.app.AlertDialog
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.webkit.MimeTypeMap
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isVisible
//import com.google.android.gms.tasks.OnSuccessListener
//import com.google.firebase.firestore.QueryDocumentSnapshot
//import com.google.firebase.firestore.SetOptions
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
//
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_setting.*
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//class Image {
//
//
//
//
//    class SettingActivity : AppCompatActivity() {
//
//
//        lateinit var initFullName: String
//        lateinit var initBio: String
//        lateinit var initEmail: String
//
//        var photoMax: Int = 1
//        var photoLocation: Uri? = null
//
//        lateinit var storageRef: StorageReference
//        var photoStorageRef = FirebaseStorage.getInstance()
//        private val userProfileRef = Firebase.firestore.collection("Users")
//
//        var prevProfilePic: String = ""
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_setting)
//
//            val sharedPreferences =
//                getSharedPreferences("currentUser", Context.MODE_PRIVATE)
//            val userId = sharedPreferences.getString("id", null)
//
//            val etFullName = findViewById<TextView>(R.id.etFullName)
//            val etBio = findViewById<TextView>(R.id.etBio)
//            val etUserId = findViewById<TextView>(R.id.etUserId)
//            val etEmail = findViewById<TextView>(R.id.etEmail)
//            //val etPassword = findViewById<TextView>(R.id.etPassword)
//
//            etUserId.isEnabled = false
//            etEmail.isEnabled = false
//            etPassword.isEnabled = false
//
//            etUserId.setTextColor(resources.getColor(R.color.greyPrimary))
//            etEmail.setTextColor(resources.getColor(R.color.greyPrimary))
//            etPassword.setTextColor(resources.getColor(R.color.greyPrimary))
//
//            val docRef = userProfileRef.document(userId.toString())
//            docRef.get().addOnSuccessListener { document ->
//                if (document != null) {
//                    etFullName.text = document.getString("fullName")
//                    etBio.text = document.getString("bio")
//                    etUserId.text = document.getString("userId")
//                    etEmail.text = document.getString("email")
//                    if (document.getString("profilePic") != "") {
//                        Picasso.get().load(document.getString("profilePic"))
//                            .centerCrop().fit().into(profilePic)
//                        prevProfilePic = document.getString("profilePic").toString()
//                    }
//                    initFullName = document.getString("fullName").toString()
//                    initBio = document.getString("bio").toString()
//                    initEmail = document.getString("email").toString()
//
//                    val canChangePassword = document.getString("canChangePassword")
//                    if (canChangePassword.equals("1")) {
//                        btnNext.isEnabled
//                        btnNext.isClickable
//                        btnNext.isVisible
//
//                        btnNext.setOnClickListener {
//                            startActivity(
//                                Intent(
//                                    this@SettingActivity,
//                                    ChangePasswordActivity::class.java
//                                )
//                            )
//                        }
//                    } else {
//                        btnNext.isEnabled = false
//                        btnNext.isClickable = false
//                        btnNext.isVisible = false
//                    }
//                }
//            }
//
//
//            storageRef = FirebaseStorage.getInstance().reference.child("UsersPhoto")
//                .child(userId.toString())
//
//            tvEditProfilePic.setOnClickListener {
//                findPhoto()
//            }
//
//            btnSave.setOnClickListener {
//
//                val fullName = etFullName.text.toString()
//                val bio = etBio.text.toString()
//                val email = etEmail.text.toString()
//                //val password = etPassword.text.toString()
//                if (fullName.equals("")) {
//                    etFullName.error = "Please fill your name"
//                    etFullName.requestFocus()
//                } else {
//                    CoroutineScope(Dispatchers.IO).launch {
//                        if (photoLocation != null) {
//                            val storage = storageRef.child(
//                                System.currentTimeMillis()
//                                    .toString() + "." + getFileExtension(photoLocation)
//                            )
//
//                            photoLocation?.let { it1 ->
//                                storage.putFile(it1).addOnSuccessListener {
//                                    storage.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
//                                        CoroutineScope(Dispatchers.IO).launch {
//                                            val profilePic = uri.toString()
//                                            val editPhoto = hashMapOf("profilePic" to profilePic)
//                                            userProfileRef.document(userId.toString())
//                                                .set(editPhoto, SetOptions.merge())
//                                                .await()
//                                            Firebase.firestore.collection("SearchFriend")
//                                                .whereEqualTo("userId", userId.toString()).get()
//                                                .addOnCompleteListener {
//                                                    if (it.isSuccessful) {
//                                                        val map = HashMap<String, String>()
//                                                        map["profilePic"] = profilePic
//                                                        for (document: QueryDocumentSnapshot in it.result!!) {
//                                                            Firebase.firestore.collection("SearchFriend")
//                                                                .document(document.id)
//                                                                .set(map, SetOptions.merge())
//                                                        }
//                                                    }
//                                                }
//                                            Firebase.firestore.collection("Users")
//                                                .document(userId.toString()).collection("Following")
//                                                .get().addOnCompleteListener {
//                                                    if (it.isSuccessful) {
//                                                        val map = HashMap<String, String>()
//                                                        map["profilePic"] = profilePic
//                                                        for (document: QueryDocumentSnapshot in it.result!!) {
//                                                            Firebase.firestore.collection("Users")
//                                                                .document(document.id)
//                                                                .collection("Followers")
//                                                                .document(userId.toString())
//                                                                .set(map, SetOptions.merge())
//                                                        }
//                                                    }
//                                                }
//                                            Firebase.firestore.collection("Users")
//                                                .document(userId.toString()).collection("Followers")
//                                                .get().addOnCompleteListener {
//                                                    if (it.isSuccessful) {
//                                                        val map = HashMap<String, String>()
//                                                        map["profilePic"] = profilePic
//                                                        for (document: QueryDocumentSnapshot in it.result!!) {
//                                                            Firebase.firestore.collection("Users")
//                                                                .document(document.id)
//                                                                .collection("Following")
//                                                                .document(userId.toString())
//                                                                .set(map, SetOptions.merge())
//                                                        }
//                                                    }
//                                                }
//                                            Firebase.firestore.collection("Users")
//                                                .document(userId.toString()).collection("Chats").get()
//                                                .addOnCompleteListener {
//                                                    if (it.isSuccessful) {
//                                                        val map = HashMap<String, String>()
//                                                        map["profilePic"] = profilePic
//                                                        for (document: QueryDocumentSnapshot in it.result!!) {
//                                                            Firebase.firestore.collection("Users")
//                                                                .document(document.id)
//                                                                .collection("Chats")
//                                                                .document(userId.toString())
//                                                                .set(map, SetOptions.merge())
//                                                        }
//                                                    }
//                                                }
//                                        }
//                                    })
//                                }
//                            }
//                        }
//
//                        if (!prevProfilePic.equals("")) {
//                            val deleteRef = photoStorageRef.getReferenceFromUrl(prevProfilePic)
//                            deleteRef.delete().await()
//                        }
//                        /*storage.putFile(photoLocation).addOnSuccessListener {
//                            storage.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
//                                CoroutineScope(Dispatchers.IO).launch {
//                                    val profilePic = uri.toString()
//                                    val editPhoto = hashMapOf("profilePic" to profilePic)
//                                    userProfileRef.document(userId.toString())
//                                        .set(editPhoto, SetOptions.merge())
//                                        .await()
//                                }
//
//                            })
//                        }*/
//                    }
//                    CoroutineScope(Dispatchers.IO).launch {
//                        if (fullName != initFullName || bio != initBio || email != initEmail) {
//                            CoroutineScope(Dispatchers.IO).launch {
//                                val splitName = fullName.split(" ").toTypedArray()
//                                val firstName = splitName[0]
//
//                                val searchKeywords = generateSearchKeywords(fullName)
//                                val userMap = HashMap<String, Any>()
//                                userMap["searchKeywords"] = searchKeywords
//
//                                val editKeywords = hashMapOf("searchKeywords" to searchKeywords)
//
//                                val editFullName = hashMapOf("fullName" to fullName)
//                                val editFirstName = hashMapOf("firstName" to firstName)
//                                val editBio = hashMapOf("bio" to bio)
//                                val editEmail = hashMapOf("email" to email)
//                                //val editPassword = hashMapOf("password" to password.toString())
//
//                                userProfileRef.document(userId.toString())
//                                    .set(editFullName, SetOptions.merge())
//                                    .await()
//                                userProfileRef.document(userId.toString())
//                                    .set(editFirstName, SetOptions.merge())
//                                    .await()
//                                userProfileRef.document(userId.toString())
//                                    .set(editBio, SetOptions.merge())
//                                    .await()
//                                userProfileRef.document(userId.toString())
//                                    .set(editEmail, SetOptions.merge())
//                                    .await()
//                                //userProfileRef.document(userId.toString()).set(editPassword, SetOptions.merge())
//                                //   .await()
//
//                                Firebase.firestore.collection("SearchFriend")
//                                    .whereEqualTo("userId", userId.toString()).get()
//                                    .addOnCompleteListener {
//                                        if (it.isSuccessful) {
//                                            for (document: QueryDocumentSnapshot in it.result!!) {
//                                                Firebase.firestore.collection("SearchFriend")
//                                                    .document(document.id)
//                                                    .set(editKeywords, SetOptions.merge())
//                                                Firebase.firestore.collection("SearchFriend")
//                                                    .document(document.id)
//                                                    .set(editFullName, SetOptions.merge())
//                                            }
//                                        }
//                                    }
//
//                                Firebase.firestore.collection("Users").document(userId.toString())
//                                    .collection("Following").get().addOnCompleteListener {
//                                        if (it.isSuccessful) {
//                                            for (document: QueryDocumentSnapshot in it.result!!) {
//                                                Firebase.firestore.collection("Users")
//                                                    .document(document.id)
//                                                    .collection("Followers").document(userId.toString())
//                                                    .set(editFullName, SetOptions.merge())
//                                            }
//                                        }
//                                    }
//                                Firebase.firestore.collection("Users").document(userId.toString())
//                                    .collection("Followers").get().addOnCompleteListener {
//                                        if (it.isSuccessful) {
//                                            for (document: QueryDocumentSnapshot in it.result!!) {
//                                                Firebase.firestore.collection("Users")
//                                                    .document(document.id)
//                                                    .collection("Following").document(userId.toString())
//                                                    .set(editFullName, SetOptions.merge())
//                                            }
//                                        }
//                                    }
//                                Firebase.firestore.collection("Users").document(userId.toString())
//                                    .collection("Chats").get().addOnCompleteListener {
//                                        if (it.isSuccessful) {
//                                            for (document: QueryDocumentSnapshot in it.result!!) {
//                                                Firebase.firestore.collection("Users")
//                                                    .document(document.id)
//                                                    .collection("Chats").document(userId.toString())
//                                                    .set(editFullName, SetOptions.merge())
//                                            }
//                                        }
//                                    }
//                            }
//                        }
//                        val intent = Intent(this@SettingActivity, BottomNavActivity::class.java)
//                        intent.putExtra("goToProfile", "True")
//                        startActivity(intent)
//                        finishAffinity()
//                    }
//                }
//            }
//
//            btnBack.setOnClickListener {
//                onBackPressed()
//            }
//
//            btnLogout.setOnClickListener {
//                val sharedPref = getSharedPreferences("currentUser", Context.MODE_PRIVATE)
//                sharedPref!!.edit().apply {
//                    putString("id", null)
//                    apply()
//                }
//                FirebaseAuth.getInstance().signOut()
//                startActivity(Intent(this@SettingActivity, LoginActivity::class.java))
//                finishAffinity()
//            }
//        }
//
//        fun getFileExtension(uri: Uri?): String? {
//            val contentResolver = contentResolver
//            val mimeTypeMap = MimeTypeMap.getSingleton()
//            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri!!))
//        }
//
//        fun findPhoto() {
//            val pic = Intent()
//            pic.type = "image/*"
//            pic.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(pic, photoMax)
//        }
//
//        override fun onActivityResult(
//            requestCode: Int,
//            resultCode: Int,
//            data: Intent?
//        ) {
//            super.onActivityResult(requestCode, resultCode, data)
//            if (requestCode == photoMax && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
//                photoLocation = data.data!!
//                Picasso.get().load(photoLocation).centerCrop().fit().into(profilePic)
//            }
//        }
//
//        private fun generateSearchKeywords(inputText: String): List<String> {
//            var inputString = inputText.toLowerCase()
//            var keywords = mutableListOf<String>()
//
//            //Split all words from the string
//            val words = inputString.split(" ")
//
//            //for each word
//            for (word in words) {
//                var appendString = ""
//
//                //for every character in the whole string
//                for (charPosition in inputString.indices) {
//                    appendString += inputString[charPosition].toString()
//                    keywords.add(appendString)
//                }
//
//                //Remove first word from the string
//                inputString = inputString.replace("$word ", "")
//            }
//            return keywords
//        }
//
//        override fun onBackPressed() {
//            AlertDialog.Builder(this)
//                .setTitle("Save Changes?")
//                .setMessage("Save your changes or discard them?")
//                .setPositiveButton(
//                    "Save"
//                )
//                { dialog, which ->
//                    run {
//                        val fullName = etFullName.text.toString()
//                        val bio = etBio.text.toString()
//                        val email = etEmail.text.toString()
//                        //val password = etPassword.text.toString()
//
//                        val sharedPreferences =
//                            getSharedPreferences("currentUser", Context.MODE_PRIVATE)
//                        val userId = sharedPreferences.getString("id", null)
//
//                        if (fullName.equals("")) {
//                            etFullName.error = "Please fill your name"
//                            etFullName.requestFocus()
//                        } else {
//                            CoroutineScope(Dispatchers.IO).launch {
//                                if (photoLocation != null) {
//                                    val storage = storageRef.child(
//                                        System.currentTimeMillis()
//                                            .toString() + "." + getFileExtension(photoLocation)
//                                    )
//
//                                    photoLocation?.let { it1 ->
//                                        storage.putFile(it1).addOnSuccessListener {
//                                            storage.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
//                                                CoroutineScope(Dispatchers.IO).launch {
//                                                    val profilePic = uri.toString()
//                                                    val editPhoto =
//                                                        hashMapOf("profilePic" to profilePic)
//                                                    userProfileRef.document(userId.toString())
//                                                        .set(editPhoto, SetOptions.merge())
//                                                        .await()
//                                                    Firebase.firestore.collection("SearchFriend")
//                                                        .whereEqualTo("userId", userId.toString()).get()
//                                                        .addOnCompleteListener {
//                                                            if (it.isSuccessful) {
//                                                                val map = HashMap<String, String>()
//                                                                map["profilePic"] = profilePic
//                                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                                    Firebase.firestore.collection("SearchFriend")
//                                                                        .document(document.id)
//                                                                        .set(map, SetOptions.merge())
//                                                                }
//                                                            }
//                                                        }
//                                                    Firebase.firestore.collection("Users")
//                                                        .document(userId.toString())
//                                                        .collection("Following").get()
//                                                        .addOnCompleteListener {
//                                                            if (it.isSuccessful) {
//                                                                val map = HashMap<String, String>()
//                                                                map["profilePic"] = profilePic
//                                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                                    Firebase.firestore.collection("Users")
//                                                                        .document(document.id)
//                                                                        .collection("Followers")
//                                                                        .document(userId.toString())
//                                                                        .set(map, SetOptions.merge())
//                                                                }
//                                                            }
//                                                        }
//                                                    Firebase.firestore.collection("Users")
//                                                        .document(userId.toString())
//                                                        .collection("Followers").get()
//                                                        .addOnCompleteListener {
//                                                            if (it.isSuccessful) {
//                                                                val map = HashMap<String, String>()
//                                                                map["profilePic"] = profilePic
//                                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                                    Firebase.firestore.collection("Users")
//                                                                        .document(document.id)
//                                                                        .collection("Following")
//                                                                        .document(userId.toString())
//                                                                        .set(map, SetOptions.merge())
//                                                                }
//                                                            }
//                                                        }
//                                                    Firebase.firestore.collection("Users")
//                                                        .document(userId.toString()).collection("Chats")
//                                                        .get().addOnCompleteListener {
//                                                            if (it.isSuccessful) {
//                                                                val map = HashMap<String, String>()
//                                                                map["profilePic"] = profilePic
//                                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                                    Firebase.firestore.collection("Users")
//                                                                        .document(document.id)
//                                                                        .collection("Chats")
//                                                                        .document(userId.toString())
//                                                                        .set(map, SetOptions.merge())
//                                                                }
//                                                            }
//                                                        }
//                                                }
//                                            })
//                                        }
//                                    }
//                                }
//
//                                if (!prevProfilePic.equals("")) {
//                                    val deleteRef = photoStorageRef.getReferenceFromUrl(prevProfilePic)
//                                    deleteRef.delete().await()
//                                }
//                            }
//                            if (fullName != initFullName || bio != initBio || email != initEmail) {
//                                CoroutineScope(Dispatchers.IO).launch {
//                                    val splitName = fullName.split(" ").toTypedArray()
//                                    val firstName = splitName[0]
//
//                                    val searchKeywords = generateSearchKeywords(fullName)
//                                    val userMap = HashMap<String, Any>()
//                                    userMap["searchKeywords"] = searchKeywords
//
//                                    val editKeywords = hashMapOf("searchKeywords" to searchKeywords)
//
//                                    val editFullName = hashMapOf("fullName" to fullName)
//                                    val editFirstName = hashMapOf("firstName" to firstName)
//                                    val editBio = hashMapOf("bio" to bio)
//                                    val editEmail = hashMapOf("email" to email)
//
//                                    userProfileRef.document(userId.toString())
//                                        .set(editFullName, SetOptions.merge())
//                                        .await()
//                                    userProfileRef.document(userId.toString())
//                                        .set(editFirstName, SetOptions.merge())
//                                        .await()
//                                    userProfileRef.document(userId.toString())
//                                        .set(editBio, SetOptions.merge())
//                                        .await()
//                                    userProfileRef.document(userId.toString())
//                                        .set(editEmail, SetOptions.merge())
//                                        .await()
//
//                                    Firebase.firestore.collection("SearchFriend")
//                                        .whereEqualTo("userId", userId.toString()).get()
//                                        .addOnCompleteListener {
//                                            if (it.isSuccessful) {
//                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                    Firebase.firestore.collection("SearchFriend")
//                                                        .document(document.id)
//                                                        .set(editKeywords, SetOptions.merge())
//                                                    Firebase.firestore.collection("SearchFriend")
//                                                        .document(document.id)
//                                                        .set(editFullName, SetOptions.merge())
//                                                }
//                                            }
//                                        }
//
//                                    Firebase.firestore.collection("Users").document(userId.toString())
//                                        .collection("Following").get().addOnCompleteListener {
//                                            if (it.isSuccessful) {
//                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                    Firebase.firestore.collection("Users")
//                                                        .document(document.id).collection("Followers")
//                                                        .document(userId.toString())
//                                                        .set(editFullName, SetOptions.merge())
//                                                }
//                                            }
//                                        }
//                                    Firebase.firestore.collection("Users").document(userId.toString())
//                                        .collection("Followers").get().addOnCompleteListener {
//                                            if (it.isSuccessful) {
//                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                    Firebase.firestore.collection("Users")
//                                                        .document(document.id).collection("Following")
//                                                        .document(userId.toString())
//                                                        .set(editFullName, SetOptions.merge())
//                                                }
//                                            }
//                                        }
//                                    Firebase.firestore.collection("Users").document(userId.toString())
//                                        .collection("Chats").get().addOnCompleteListener {
//                                            if (it.isSuccessful) {
//                                                for (document: QueryDocumentSnapshot in it.result!!) {
//                                                    Firebase.firestore.collection("Users")
//                                                        .document(document.id).collection("Chats")
//                                                        .document(userId.toString())
//                                                        .set(editFullName, SetOptions.merge())
//                                                }
//                                            }
//                                        }
//                                }
//                            }
//                            val intent = Intent(this@SettingActivity, BottomNavActivity::class.java)
//                            intent.putExtra("goToProfile", "True")
//                            startActivity(intent)
//                            finishAffinity()
//                        }
//                    }
//                }
//                .setNeutralButton("Cancel", null)
//                .setNegativeButton("Discard") { dialog, which -> super.onBackPressed() }
//                .show()
//        }
//    }
//}