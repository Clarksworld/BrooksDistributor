package com.blockmay.brooksdistributor.repositories

import com.blockmay.brooksdistributor.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {

        return withContext(Dispatchers.IO){

            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable){

                when(throwable){

                    is HttpException -> {
                        Resource.Error(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else-> {
                        Resource.Error(true, null, null)
                    }
                }
            }
        }
    }
}