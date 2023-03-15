package com.blockmay.brooksdistributor.repositories

import com.blockmay.brooksdistributor.model.add_token_model.AddTokenModel
import com.blockmay.brooksdistributor.network.api.Api
import com.blockmay.brooksdistributor.network.response.add_tokrn_response.AddTokenResponse
import com.blockmay.waterorder.data.UserPreferences
import retrofit2.http.Body
import retrofit2.http.Path

class OrderRepositories(
    private val api : Api,
    private val userPreferences: UserPreferences
): BaseRepository() {


    suspend fun usersLogin(
        loginCode: String

    ) = safeApiCall{
        api.userLogin(loginCode)

    }

    suspend fun available(
       status: String
    ) = safeApiCall {

        api.available(status)

    }

    suspend fun orderStatusUpdate(
        id: String

    ) = safeApiCall {

        api.orderStatusUpdate(id)
    }

    suspend fun addToken(
        addTokenModel: AddTokenModel
    ) = safeApiCall {

        api.addToken(addTokenModel)
    }


    suspend fun orderStatusUpdater(
       id: String
    ) = safeApiCall {

        api.orderStatusUpdater(id)
    }
//    suspend fun getBottleSizes() = safeApiCall{
//        api.getBottleSizes()
//
//    }
//
//    suspend fun makeOrder(
//         makeOrderModel: MakeOrderModel
//    ) = safeApiCall {
//
//        api.makeOrder(makeOrderModel)
//    }


//    suspend fun uploadPaymentProof(
//        file: File,
//        id: String
//    ) = safeApiCall {
//
//        api.uploadPaymentProof(
//            image = MultipartBody.Part.createFormData(
//                "proof",
//                file.name,
//                file.asRequestBody(),
//
//                ),
//            id
//        )
//
//    }

//        suspend fun makeOrderTransfer(
//            size: String?,
//            quantity: Int?,
//            address: String?,
//            payment: String?,
//            imageUri: File?
//
//
//        ) = safeApiCall {
//
//            api.makeOrderTransfer(
//                size,
//                quantity,
//                address,
//                payment,
//                imageUri
//            )
//        }




//
//    suspend fun getOrders(limit: Int) = safeApiCall {
//        api.getOrders(limit)
//
//    }
//
//    suspend fun paymentOptions() = safeApiCall {
//
//        api.paymentOptions()
//    }



    suspend fun saveAuthToken(token: String){
        userPreferences.safeAuthToken(token)
    }

    suspend fun saveFirstName(firstName: String){

        userPreferences.saveFirstName(firstName)

    }

    suspend fun saveLastName(lastName: String){

        userPreferences.saveLastName(lastName)

    }

    suspend fun saveEmail(email: String){

        userPreferences.saveEmail(email)

    }

    suspend fun saveUserPhone(phoneNumber: String){

        userPreferences.saveUserPhone(phoneNumber)

    }


    suspend fun notiToken(notiToken: String){

        userPreferences.notiToken(notiToken)
    }


}