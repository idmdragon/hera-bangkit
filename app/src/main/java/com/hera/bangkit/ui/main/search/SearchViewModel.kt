package com.hera.bangkit.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.entity.HastagEntity
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.utils.DummyData
import com.hera.bangkit.utils.DummyHastag

class SearchViewModel : ViewModel() {


    fun getHastag():LiveData<ArrayList<HastagEntity>> {
         val _listHastag = MutableLiveData<ArrayList<HastagEntity>>()
         val arrHastag = DummyHastag.hastagList()
        _listHastag.postValue(arrHastag)
        return _listHastag
    }
}