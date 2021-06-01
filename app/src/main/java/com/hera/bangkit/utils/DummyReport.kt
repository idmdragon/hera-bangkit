package com.hera.bangkit.utils


object DummyReport {
    fun generateReportDummy(): ArrayList<ReportEntity> {
        val dummyUser = DummyUser.generateUser()
        val arrReport = ArrayList<ReportEntity>()
        arrReport.add(
                ReportEntity(
                        dummyUser.NIK,
                        dummyUser.NamaLengkap,
                        dummyUser.TanggalLahir,
                        dummyUser.NomerTelepon,
                        dummyUser.Alamat,
                        "Exploitasi Ekonomi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        DateHelper.getCurrentDate()
                )
        )
        arrReport.add(
                ReportEntity(
                        dummyUser.NIK,
                        dummyUser.NamaLengkap,
                        dummyUser.TanggalLahir,
                        dummyUser.NomerTelepon,
                        dummyUser.Alamat,
                        "Pornografi",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        DateHelper.getCurrentDate()
                )
        )

        arrReport.add(
                ReportEntity(
                        dummyUser.NIK,
                        dummyUser.NamaLengkap,
                        dummyUser.TanggalLahir,
                        dummyUser.NomerTelepon,
                        dummyUser.Alamat,
                        "Kejahatan Seksual",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        DateHelper.getCurrentDate()
                )
        )
        return arrReport
    }
}