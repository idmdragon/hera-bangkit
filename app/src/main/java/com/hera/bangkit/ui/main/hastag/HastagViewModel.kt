package com.hera.bangkit.ui.main.hastag

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.repositories.DefaultRepository
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.remote.response.HastagEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HastagViewModel @Inject constructor(
    private val repository: DefaultRepository
): ViewModel() {

    fun getHastag(arrHastag: Array<String>): LiveData<ArrayList<HastagEntity>> {
        val _listHastag = MutableLiveData<ArrayList<HastagEntity>>()
        val listHastag = ArrayList<HastagEntity>()

            CoroutineScope(Dispatchers.IO).launch {
                for (tag in arrHastag) {
                val size = repository.getHastagSize(tag)

                val item = HastagEntity(tag,size)
                    listHastag.add(item)
                }
                listHastag.sortByDescending { it.Count }
                _listHastag.postValue(listHastag)

        }

        return _listHastag
    }

}