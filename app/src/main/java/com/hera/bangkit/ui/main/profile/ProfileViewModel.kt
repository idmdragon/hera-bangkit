package com.hera.bangkit.ui.main.profile

import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.repositories.DefaultRepository
import com.hera.bangkit.data.response.StoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {
    fun getUser(uid : String) = repository.getUser(uid)
}