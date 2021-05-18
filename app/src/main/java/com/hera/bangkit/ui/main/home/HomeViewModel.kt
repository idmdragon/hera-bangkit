package com.hera.bangkit.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.utils.DummyData

class HomeViewModel : ViewModel() {

    private val _listStory = MutableLiveData<ArrayList<StoryEntity>>()
    fun getStoryList():LiveData<ArrayList<StoryEntity>> {
         val arrTemp = DummyData.generateHomeDummy()
        _listStory.postValue(arrTemp)
        return _listStory
    }
}