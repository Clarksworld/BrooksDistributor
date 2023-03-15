package com.blockmay.brooksdistributor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blockmay.brooksdistributor.databinding.AvailableOrdersLayoutBinding
import com.blockmay.brooksdistributor.network.response.available_order_response.Data
import com.blockmay.brooksdistributor.onclicks.AvailableOrdersClicks

class AvailableOrdersAdapter(private val availableOrdersClicks: AvailableOrdersClicks):
ListAdapter<Data, AvailableOrdersAdapter.AvailableOrdersHolder>(MyDiffUtil){


    companion object MyDiffUtil : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {

            return oldItem.id == newItem.id
        }

    }

    inner class AvailableOrdersHolder(private val binding: AvailableOrdersLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

                fun bind(data: Data){

                    var fName = data.user.firstName
                    var lName = data.user.lastName

                    var size = data.size
                    var quantity = data.quantity
                    var phoneNumber = data.user.phone
                    var address = data.address

                    binding.textName.text = "$fName $lName"
                    binding.textSize.text = size
                    binding.textQuantity.text = quantity.toString()
                    binding.textPhone.text = phoneNumber
                    binding.textAddress.text = address


                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableOrdersHolder {

        return AvailableOrdersHolder(
            AvailableOrdersLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AvailableOrdersHolder, position: Int) {

        val availableOrders = getItem(position)
        holder.itemView.setOnClickListener {
            availableOrdersClicks.availableOrderClicks(availableOrders)
        }

        holder.bind(availableOrders)
    }
}