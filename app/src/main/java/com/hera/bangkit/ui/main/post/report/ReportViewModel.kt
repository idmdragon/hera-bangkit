package com.hera.bangkit.ui.main.post.report

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.data.repositories.DefaultRepository
import com.hera.bangkit.utils.DummyUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {
    fun insertReport (reportEntity: ReportEntity) = repository.insertReport(reportEntity)


    private val listReport = MutableLiveData<ArrayList<ReportEntity>>()

    fun getReport(): LiveData<ArrayList<ReportEntity>> {
        val listItem = ArrayList<ReportEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("report")
                .whereEqualTo("fullname",DummyUser.generateUser().Fullname)
                .get()
                .await()
            for(document in querySnapshot.documents){
                Log.d("REPORTVIEWMODEL","isi doc $document")
                val item = document.toObject<ReportEntity>()
                Log.d("REPORTVIEWMODEL","isi item $item")
                if (item != null) {
                    listItem.add(item)
                }

            }
            listReport.postValue(listItem)
        }
        return listReport
    }

}





