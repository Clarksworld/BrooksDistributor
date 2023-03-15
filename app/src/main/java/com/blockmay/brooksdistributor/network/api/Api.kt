package com.blockmay.brooksdistributor.network.api

import com.blockmay.brooksdistributor.model.add_token_model.AddTokenModel
import com.blockmay.brooksdistributor.network.response.SignInResponse
import com.blockmay.brooksdistributor.network.response.add_tokrn_response.AddTokenResponse
import com.blockmay.brooksdistributor.network.response.available_order_response.AvailableOrdersResponse
import com.blockmay.brooksdistributor.network.response.order_status_update.OrderStatusUpdate
import com.blockmay.brooksdistributor.network.response.order_status_updater.OrderStatusUpdater
import retrofit2.http.*

interface Api {

    @GET("api/v1/driver/all-orders?")
    suspend fun available(
        @Query("status")status: String
    ): AvailableOrdersResponse

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("api/v1/order/signin")
    suspend fun userLogin(
        @Field("login_code") loginCode: String
    ): SignInResponse


    @POST("api/v1/driver/order/update/status/{id}")
    suspend fun orderStatusUpdate(
        @Path("id")id: String
    ): OrderStatusUpdate

    @POST("api/v1/device/create-token")
    suspend fun addToken(
        @Body addTokenModel: AddTokenModel
    ): AddTokenResponse



    @POST("api/v1/driver/order/options/{id}")
    suspend fun orderStatusUpdater(
        @Path("id")id: String
    ): OrderStatusUpdater


}