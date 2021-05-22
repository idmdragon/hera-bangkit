package com.idm.moviedb.data.source.remote

class RemoteResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): RemoteResponse<T> = RemoteResponse(StatusResponse.SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): RemoteResponse<T> = RemoteResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String, body: T): RemoteResponse<T> = RemoteResponse(StatusResponse.ERROR, body, msg)
    }
}