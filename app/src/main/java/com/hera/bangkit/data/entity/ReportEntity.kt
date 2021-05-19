package com.hera.bangkit.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntity(
    val NIK : String,
    val Nama : String,
    val TTL : String,
    val NoTlp : String,
    val Alamat : String,
    val Kategori : String,
    val Kronologi : String,
    val TanggalLapor : String
) : Parcelable{
}