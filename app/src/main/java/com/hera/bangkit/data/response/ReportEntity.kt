package com.hera.bangkit.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntity(
    val address : String ="",
    val category : String ="",
    val description : String ="",
    val fullname : String ="",
    val nik : String = "",
    val ttl : String = "",
    val phoneNumber : String ="",
    val uploadTime : String =""
) : Parcelable{
}