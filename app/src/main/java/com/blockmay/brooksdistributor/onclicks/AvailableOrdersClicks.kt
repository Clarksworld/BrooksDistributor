package com.blockmay.brooksdistributor.onclicks

import com.blockmay.brooksdistributor.network.response.available_order_response.Data

class AvailableOrdersClicks (val availableOrderClicks: (data: Data) -> Unit){

    fun onClick(data: Data) = availableOrderClicks(data)

}