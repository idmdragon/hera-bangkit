package com.hera.bangkit.ui.main.post.sos

import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SosViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {

    fun getUser(uid : String) = repository.getUser(uid)

}