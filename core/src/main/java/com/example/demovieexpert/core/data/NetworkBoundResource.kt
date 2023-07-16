package com.example.demovieexpert.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.demovieexpert.core.data.source.remote.network.ApiResponse
import com.example.demovieexpert.core.utils.AppExecutors
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private var result: Flow<com.example.demovieexpert.core.data.Resource<ResultType>> = flow {
        emit(com.example.demovieexpert.core.data.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(com.example.demovieexpert.core.data.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        com.example.demovieexpert.core.data.Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        com.example.demovieexpert.core.data.Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.example.demovieexpert.core.data.Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { com.example.demovieexpert.core.data.Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<com.example.demovieexpert.core.data.Resource<ResultType>> = result

}