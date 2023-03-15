package com.blockmay.brooksdistributor.network.response.available_order_response

data class User(
    val address: String,
    val auth_code: String,
    val city: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    val ref_code: String,
    val referrerId: String,
    val role: String,
    val state: String,
    val status: String,
    val updatedAt: String,
    val upline_code: String
)