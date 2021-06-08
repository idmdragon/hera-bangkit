package com.hera.bangkit.ui.main.post.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.source.local.entity.ReportEntity
import com.hera.bangkit.data.repositories.DefaultRepository
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

    fun getReport(fullName : String): LiveData<ArrayList<ReportEntity>> {
        val listItem = ArrayList<ReportEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("report")
                .whereEqualTo("fullname",fullName)
                .get()
                .await()
            for(document in querySnapshot.documents){
                val item = document.toObject<ReportEntity>()
                if (item != null) {
                    listItem.add(item)
                }
            }
            listReport.postValue(listItem)
        }
        return listReport
    }
        fun getUser(uid : String) = repository.getUser(uid)
}





