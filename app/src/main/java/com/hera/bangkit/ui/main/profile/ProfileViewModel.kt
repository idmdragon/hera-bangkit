package com.hera.bangkit.ui.main.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.repositories.DefaultRepository
import com.hera.bangkit.data.source.local.entity.UserEntity
import com.hera.bangkit.data.source.remote.response.StoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {
    fun getUser(uid : String) = repository.getUser(uid)

    fun updateUser(user: UserEntity) {
        val storyCollectionRef = Firebase.firestore.collection("users")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("email", user.email)
                .whereEqualTo("uid", user.uid)
                .get()
                .await()
            for (document in querySnapshot) {
                val userRef = storyCollectionRef.document(document.id)
                userRef.update("dateOfBirth", user.dateOfBirth)
                userRef.update("fullName", user.fullName)
                userRef.update("guardianName1", user.guardianName1)
                userRef.update("guardianName2", user.guardianName2)
                userRef.update("guardianPhoneNumber1", user.guardianPhoneNumber1)
                userRef.update("guardianPhoneNumber2", user.guardianPhoneNumber2)
                userRef.update("nik", user.nik)
                userRef.update("phoneNumber", user.phoneNumber)
                userRef.update("placeOfBirth", user.placeOfBirth)
                userRef.update("username", user.username)
                userRef.update("avatar", user.avatar)
            }
        }

    }
}