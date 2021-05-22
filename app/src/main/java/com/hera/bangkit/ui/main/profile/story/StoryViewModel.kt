package com.hera.bangkit.ui.main.profile.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.utils.DummyData
import com.hera.bangkit.utils.DummyUser

class StoryViewModel : ViewModel() {

    private val _listStory = MutableLiveData<ArrayList<StoryEntity>>()
    fun getStoryList():LiveData<ArrayList<StoryEntity>> {
//         val arrTemp = DummyData.generateHomeDummy()
         val arrItem = ArrayList<StoryEntity>()
//            for (item in arrTemp){
//                if (item.Username == DummyUser.generateUser().NamaPengguna){
//                    arrItem.add(item)
//                }
//            }
        _listStory.postValue(arrItem)
        return _listStory
    }
}