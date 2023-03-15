package com.blockmay.brooksdistributor.network.response.order_status_update

data class OrderStatusUpdate(
    val message: String,
    val order: Order,
    val status: Int
)