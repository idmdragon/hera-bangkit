package com.hera.bangkit.utils

import com.hera.bangkit.data.response.UserEntity

object DummyUser {
    fun generateUser():UserEntity{
        return UserEntity(
            "Jl.Catur Jaya No 110 Kota Madiun",
            "12491249",
            "35771505103541",
            "Ilham Dwi Muchlison",
            "Idmdragon",
            "08124937645",
            "16/04/2000",

        )
    }
}