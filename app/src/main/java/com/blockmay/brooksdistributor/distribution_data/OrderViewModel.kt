package com.blockmay.brooksdistributor.distribution_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blockmay.brooksdistributor.model.add_token_model.AddTokenModel
import com.blockmay.brooksdistributor.network.Resource
import com.blockmay.brooksdistributor.network.response.SignInResponse
import com.blockmay.brooksdistributor.network.response.add_tokrn_response.AddTokenResponse
import com.blockmay.brooksdistributor.network.response.available_order_response.AvailableOrdersResponse
import com.blockmay.brooksdistributor.network.response.order_status_update.OrderStatusUpdate
import com.blockmay.brooksdistributor.network.response.order_status_updater.OrderStatusUpdater
import com.blockmay.brooksdistributor.repositories.OrderRepositories
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepositories: OrderRepositories): ViewModel() {

    private val _userLoginResponse: MutableLiveData<Resource<SignInResponse>> = MutableLiveData()
    val userLoginResponse: LiveData<Resource<SignInResponse>> get() = _userLoginResponse
//
//  get  private val _getBottleSizeResponse: MutableLiveData<Resource<BottleSizeResponse>> = MutableLiveData()
//    val getBottleSizeResponse: LiveData<Resource<BottleSizeResponse>> get() = _getBottleSizeResponse
//
//
//    private val _createOrderResponse: MutableLiveData<Resource<MakeOrderResponse>> = MutableLiveData()
//    val createOrderResponse: LiveData<Resource<MakeOrderResponse>> get() = _createOrderResponse
//
//
//    private val _orderHistoryResponse: MutableLiveData<Resource<OrderHistoryResponse>> = MutableLiveData()
//    val orderHistoryResponse: LiveData<Resource<OrderHistoryResponse>> get() = _orderHistoryResponse
//
//    private val _paymentOptionsResponse: MutableLiveData<Resource<PaymentOptionResponse>> = MutableLiveData()
//    val paymentOptionsResponse: LiveData<Resource<PaymentOptionResponse>> get() = _paymentOptionsResponse
//
//
//    private val _paymentUploadResponse: MutableLiveData<Resource<PaymentProofResponse>> = MutableLiveData()
//    val paymentUploadResponse: LiveData<Resource<PaymentProofResponse>> get() = _paymentUploadResponse



    private val _availableOrdersResponse: MutableLiveData<Resource<AvailableOrdersResponse>> = MutableLiveData()
    val availableOrdersResponse: LiveData<Resource<AvailableOrdersResponse>> get() = _availableOrdersResponse


    private val _orderStatusResponse: MutableLiveData<Resource<OrderStatusUpdate>> = MutableLiveData()
    val orderStatusResponse: LiveData<Resource<OrderStatusUpdate>> get() = _orderStatusResponse

    private val _addTokenResponse: MutableLiveData<Resource<AddTokenResponse>> = MutableLiveData()
    val addTokenResponse: LiveData<Resource<AddTokenResponse>> get() = _addTokenResponse


    private val _orderStatusUpdateResponse: MutableLiveData<Resource<OrderStatusUpdater>> = MutableLiveData()
    val orderStatusUpdateResponse: LiveData<Resource<OrderStatusUpdater>> get() = _orderStatusUpdateResponse


    fun orderStatusUpdater(
        id: String
    ) = viewModelScope.launch {

        _orderStatusUpdateResponse.value = Resource.Loading
        _orderStatusUpdateResponse.value = orderRepositories.orderStatusUpdater(id)
    }

    fun addToken(
        addTokenModel: AddTokenModel
    ) = viewModelScope.launch {

        _addTokenResponse.value = Resource.Loading
        _addTokenResponse.value = orderRepositories.addToken(addTokenModel)
    }


    fun orderStatusUpdate(
        id: String

    ) = viewModelScope.launch {

        _orderStatusResponse.value  = Resource.Loading
        _orderStatusResponse.value = orderRepositories.orderStatusUpdate(id)
    }


    fun registerUsers(
        loginCode: String
    ) = viewModelScope.launch {

        _userLoginResponse.value = Resource.Loading
        _userLoginResponse.value = orderRepositories.usersLogin (loginCode)

    }

     fun available(
        status: String
    ) = viewModelScope.launch {

        _availableOrdersResponse.value = Resource.Loading
        _availableOrdersResponse.value = orderRepositories.available(status)


    }

//    fun getBottleSize() = viewModelScope.launch {
//
//        _getBottleSizeResponse.value = Resource.Loading
//        _getBottleSizeResponse.value = orderRepositories.getBottleSizes()
//    }


//     fun makeOrder(
//        makeOrderModel: MakeOrderModel
//    ) = viewModelScope.launch {
//
//        _createOrderResponse.value = Resource.Loading
//        _createOrderResponse.value = orderRepositories.makeOrder(makeOrderModel)
//
//    }

//    fun uploadFile(
//        file: File,
//        id: String
//
//    ) = viewModelScope.launch {
//
//        _paymentUploadResponse.value = Resource.Loading
//
//        _paymentUploadResponse.value = orderRepositories.uploadPaymentProof(file, id)
//
//    }


//     fun makeOrderTransfer(
//        size: String?,
//        quantity: Int?,
//        address: String?,
//        payment: String?,
//        imageUri: File?
//
//
//    ) =  viewModelScope.launch {
//
//        _createOrderResponse.value = Resource.Loading
//        _createOrderResponse.value = orderRepositories.makeOrderTransfer(
//            size,
//            quantity,
//            address,
//            payment,
//            imageUri
//        )
//
//    }


//   fun getOrders(limit: Int) = viewModelScope.launch{
//
//        _orderHistoryResponse.value = Resource.Loading
//        _orderHistoryResponse.value = orderRepositories.getOrders(limit)
//
//    }

    fun saveAuthToken(token: String) = viewModelScope.launch {

        orderRepositories.saveAuthToken(token)
    }

    fun saveFirsName(firstName: String) = viewModelScope.launch{

        orderRepositories.saveFirstName(firstName)

    }

     fun saveLastName(lastName: String) = viewModelScope.launch{

        orderRepositories.saveLastName(lastName)

    }

    fun saveEmail(email: String) = viewModelScope.launch{

       orderRepositories.saveEmail(email)

    }


     fun saveUserPhone(phoneNumber: String) = viewModelScope.launch{

        orderRepositories.saveUserPhone(phoneNumber)

    }

    fun notiToken(notiToken: String) = viewModelScope.launch(){

        orderRepositories.notiToken(notiToken)
    }


}