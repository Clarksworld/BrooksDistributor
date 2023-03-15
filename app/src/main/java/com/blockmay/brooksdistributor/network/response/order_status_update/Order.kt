package com.blockmay.brooksdistributor.network.response.order_status_update

data class Order(
    val address: String,
    val createdAt: String,
    val driverId: Any,
    val id: String,
    val payment_method: String,
    val price: Int,
    val quantity: Int,
    val ref_no: String,
    val size: String,
    val status: String,
    val total_amount: Int,
    val updatedAt: String,
    val upline_code: String,
    val userId: String
)