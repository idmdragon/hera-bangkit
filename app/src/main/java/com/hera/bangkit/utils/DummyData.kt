package com.hera.bangkit.utils

import com.hera.bangkit.data.entity.StoryEntity

object DummyData {
    fun generateHomeDummy() : ArrayList<StoryEntity> {

        val arrStory = ArrayList<StoryEntity>()
        arrStory.add(
                StoryEntity(
                        1,
                        "IlhamDwi",
                        "https://image.flaticon.com/icons/png/512/194/194938.png",
                        "16:20",
                        "Sexual Harrasment",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        "https://cdn-image.hipwee.com/wp-content/uploads/2016/05/hipwee-www.malemodelscene.net_-750x507.jpg",
                        5
                )
        )
        arrStory.add(
                StoryEntity(
                        2,
                        "Rafifaz",
                        "https://image.flaticon.com/icons/png/512/194/194938.png",
                        "16:20",
                        "CatCall Harrasment",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        "https://cdn-image.hipwee.com/wp-content/uploads/2016/05/hipwee-www.malemodelscene.net_-750x507.jpg",
                        5
                )
        )

        arrStory.add(
            StoryEntity(
                3,
                "FASSZ",
                "https://image.flaticon.com/icons/png/512/194/194938.png",
                "16:20",
                "CatCall Harrasment",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                "",
                6
            )
        )

        arrStory.add(
                StoryEntity(
                        3,
                        "FASSZ",
                        "https://image.flaticon.com/icons/png/512/194/194938.png",
                        "16:20",
                        "CatCall Harrasment",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat",
                        "https://cdn-image.hipwee.com/wp-content/uploads/2016/05/hipwee-www.malemodelscene.net_-750x507.jpg",
                        6
                )
        )
        return arrStory
    }
}