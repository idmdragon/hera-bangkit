package com.hera.bangkit.utils

import com.hera.bangkit.data.response.HastagEntity

object DummyHastag {
    fun hastagList() : ArrayList<HastagEntity>{
        val hastagList = ArrayList<HastagEntity>()
        hastagList.add(
                HastagEntity(
                    "Lokasi Publik", 4000
                )
        )
        hastagList.add(
                HastagEntity(
                        "Lokasi Privat", 3000
                )
        )
        hastagList.add(
                HastagEntity(
                        "Lokasi Kerja", 2000
                )
        )

        hastagList.add(
                HastagEntity(
                        "Siber", 500
                )
        )
        hastagList.add(
                HastagEntity(
                        "Lokasi Pendidikan", 450
                )
        )
        hastagList.add(
                HastagEntity(
                        "Netral", 300
                )
        )

        return hastagList

    }
}