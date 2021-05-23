package com.hera.bangkit.utils

import com.hera.bangkit.data.response.ReportEntity


object DummyReport {
    fun generateReportDummy(): ArrayList<ReportEntity> {
        val dummyUser = DummyUser.generateUser()
        val arrReport = ArrayList<ReportEntity>()
        arrReport.add(
                ReportEntity(
                        dummyUser.NIK,
                        dummyUser.Fullname,
                        dummyUser.DateOfBirth,
                        dummyUser.PhoneNumber,
                        dummyUser.address,
                        "Exploitasi Ekonomi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        DateHelper.getCurrentDate()
                )
        )
        arrReport.add(
                ReportEntity(
                    dummyUser.NIK,
                    dummyUser.Fullname,
                    dummyUser.DateOfBirth,
                    dummyUser.PhoneNumber,
                    dummyUser.address,
                        "Pornografi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        DateHelper.getCurrentDate()
                )
        )

        arrReport.add(
                ReportEntity(
                    dummyUser.NIK,
                    dummyUser.Fullname,
                    dummyUser.DateOfBirth,
                    dummyUser.PhoneNumber,
                    dummyUser.address,
                        "Kejahatan Seksual",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        DateHelper.getCurrentDate()
                )
        )
        return arrReport
    }
}