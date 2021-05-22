package com.hera.bangkit.ui.main.search.hastagpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.utils.DummyData
import com.hera.bangkit.utils.DummyUser

class HastagPageViewModel : ViewModel(){
    private val _listStory = MutableLiveData<ArrayList<StoryEntity>>()
    fun getStoryList(hastagCategory : String): LiveData<ArrayList<StoryEntity>> {
//        val arrTemp = DummyData.generateHomeDummy()
        val arrItem = ArrayList<StoryEntity>()
//        for (item in arrTemp){
//            if (item.Category == hastagCategory){
//                arrItem.add(item)
//            }
//        }
        _listStory.postValue(arrItem)
        return _listStory
    }
}