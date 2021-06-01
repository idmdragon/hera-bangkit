package com.hera.bangkit.utils

import com.hera.bangkit.data.response.ReportEntity


object DummyReport {
    fun generateReportDummy(): ArrayList<ReportEntity> {
        val arrReport = ArrayList<ReportEntity>()
        arrReport.add(
                ReportEntity(
                        "Madiun",
                        "Kekerasan",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        "Ilham Dwi Muchlison",
                        "4623492349",
                        "16 April 20000",
                        "0581815321",
                        DateHelper.getCurrentDate()
                )
        )

        return arrReport
    }
}