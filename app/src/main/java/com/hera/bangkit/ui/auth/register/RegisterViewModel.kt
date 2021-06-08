package com.hera.bangkit.ui.auth.register

import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.repositories.DefaultRepository
import com.hera.bangkit.data.source.local.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {
    fun insertUser (userEntity: UserEntity) = repository.insertUser(userEntity)
    fun getUser(uid : String) = repository.getUser(uid)

}