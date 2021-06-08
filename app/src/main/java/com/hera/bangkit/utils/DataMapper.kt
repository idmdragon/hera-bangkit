//package com.hera.bangkit.utils
//
//import com.hera.bangkit.data.source.local.entity.StoryEntity
//import com.hera.bangkit.data.source.remote.response.StoryResponse
//
//
//object DataMapper {
//    fun mapResponsesToEntities(input: List<StoryResponse>): List<StoryEntity> {
//        val itemList = ArrayList<StoryEntity>()
//        input.map {
//          val item =   StoryEntity(
//               avatarProfile = it.,
//             category= "",
//             content="",
//             imgContent ="",
//
//             isLike = false,
//
//             isUpvoted = false,
//             like = 0,
//             timeUpload ="",
//             userName  = "",
//             upvote = 0,
//
//
//
//          )
//            itemList.add(item)
//        }
//        return itemList
//    }
//
//    fun mapEntitiesToDomain(input: List<OnePieceEntity>): List<OnePiece> =
//        input.map {
//            OnePiece(
//                airing = it.airing,
//                episodes = it.episodes,
//                image_url = it.image_url,
//                mal_id = it.mal_id,
//                members = it.members,
//                rated = it.rated,
//                score = it.score,
//                start_date = it.start_date,
//                synopsis = it.synopsis,
//                title = it.title,
//                type = it.type,
//                isFavorite = it.isFavorite
//            )
//        }
//    fun mapDomainToEntity(input: OnePiece) = OnePieceEntity(
//        airing = input.airing,
//        episodes = input.episodes,
//        image_url = input.image_url,
//        mal_id = input.mal_id,
//        members = input.members,
//        rated = input.rated,
//        score = input.score,
//        start_date = input.start_date,
//        synopsis = input.synopsis,
//        title = input.title,
//        type = input.type,
//        isFavorite = input.isFavorite
//
//    )
//}