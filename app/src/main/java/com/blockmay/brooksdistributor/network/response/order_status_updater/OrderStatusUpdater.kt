package com.blockmay.brooksdistributor.network.response.order_status_updater

data class OrderStatusUpdater(
    val message: String,
    val order: Order,
    val status: Int
)