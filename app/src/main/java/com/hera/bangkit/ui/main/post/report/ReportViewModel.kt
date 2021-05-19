package com.hera.bangkit.ui.main.post.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.entity.ReportEntity
import com.hera.bangkit.utils.DummyReport

class ReportViewModel : ViewModel() {

    private val listReport = MutableLiveData<ArrayList<ReportEntity>>()

    fun postReport(reportEntity: ReportEntity){
        val reportItem = ArrayList<ReportEntity>()
        reportItem.add(reportEntity)
        //dipush ke firebase

        //temp
        listReport.postValue(reportItem)
    }

    fun getReport(): LiveData<ArrayList<ReportEntity>> {
        //mengambil dari firebase
        val dummyItem = DummyReport.generateReportDummy()
        listReport.postValue(dummyItem)
        return listReport
    }
}