package com.blockmay.brooksdistributor.network.response.available_order_response

data class Data(
    val Payment: Payment,
    val address: String,
    val createdAt: String,
    val driver: Any,
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
    val user: User,
    val userId: String
)