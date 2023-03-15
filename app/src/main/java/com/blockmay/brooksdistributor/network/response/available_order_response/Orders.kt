package com.blockmay.brooksdistributor.network.response.available_order_response

data class Orders(
    val currentPage: Int,
    val `data`: List<Data>,
    val totalItems: Int,
    val totalPages: Int
)