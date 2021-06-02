package com.hera.bangkit.utils

import com.hera.bangkit.data.response.HastagEntity

object DummyHastag {
    fun hastagList() : ArrayList<HastagEntity>{
        val hastagList = ArrayList<HastagEntity>()
        hastagList.add(
                HastagEntity(
                    "Public Places", 4000
                )
        )
        hastagList.add(
                HastagEntity(
                        "Private Places", 3000
                )
        )
        hastagList.add(
                HastagEntity(
                        "Public Places", 2000
                )
        )
        hastagList.add(
                HastagEntity(
                        "Work Places", 1000
                )
        )
        hastagList.add(
                HastagEntity(
                        "Cyber", 500
                )
        )
        hastagList.add(
                HastagEntity(
                        "Educational Places", 450
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