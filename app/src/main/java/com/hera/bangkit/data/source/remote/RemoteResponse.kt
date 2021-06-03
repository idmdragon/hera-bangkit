package com.hera.bangkit.data.source.remote

import com.idm.moviedb.data.source.remote.StatusResponse

class RemoteResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): RemoteResponse<T> = RemoteResponse(StatusResponse.SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): RemoteResponse<T> = RemoteResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String, body: T): RemoteResponse<T> = RemoteResponse(StatusResponse.ERROR, body, msg)
    }
}