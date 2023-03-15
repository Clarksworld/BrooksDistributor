package com.blockmay.brooksdistributor.network.response.available_order_response

data class Payment(
    val amount: Int,
    val createdAt: String,
    val currency: String,
    val id: String,
    val orderId: String,
    val proof: Any,
    val ref_no: String,
    val status: String,
    val type: String,
    val updatedAt: String,
    val userId: String
)