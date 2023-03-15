package com.blockmay.brooksdistributor.network.response.available_order_response

data class AvailableOrdersResponse(
    val message: String,
    val orders: Orders,
    val status: Int
)