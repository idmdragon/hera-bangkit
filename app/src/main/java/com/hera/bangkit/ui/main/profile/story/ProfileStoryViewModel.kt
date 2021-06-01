package com.hera.bangkit.ui.main.profile.story

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.StoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileStoryViewModel : ViewModel() {

    private val _listStory = MutableLiveData<ArrayList<StoryEntity>>()
    fun getMyStory(): LiveData<ArrayList<StoryEntity>> {
        val listItem = ArrayList<StoryEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("story")
                .whereEqualTo("userID","nwDaazUrlPY3iDPDY0xn2TxoL703")
                .get()
                .await()

            for(document in querySnapshot.documents){
                val item = document.toObject<StoryResponse>()
                //dibawah sini nanti bikin collection ke user UID buat get kek ava sama username
                Log.d("ProfileStory","Isi Item $item")
                if (item != null) {
                    listItem.add(
                        StoryEntity(
                            "https://image.flaticon.com/icons/png/512/194/194938.png",
                            item.category,
                            item.content,
                            item.imgContent,
                            item.isLike,
                            item.like,
                            item.location,
                            item.timeUpload,
                            "Idmdragon16",
                        )
                    )
                }
            }
            _listStory.postValue(listItem)
        }
        return _listStory
    }
}