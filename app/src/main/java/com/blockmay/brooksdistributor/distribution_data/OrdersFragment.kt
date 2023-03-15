package com.blockmay.brooksdistributor.distribution_data

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.blockmay.brooksdistributor.R
import com.blockmay.brooksdistributor.adapter.AvailableOrdersAdapter
import com.blockmay.brooksdistributor.base.BaseFragment
import com.blockmay.brooksdistributor.databinding.FragmentDistributionDataBinding
import com.blockmay.brooksdistributor.databinding.FragmentOrdersBinding
import com.blockmay.brooksdistributor.model.add_token_model.AddTokenModel
import com.blockmay.brooksdistributor.network.Resource
import com.blockmay.brooksdistributor.network.api.Api
import com.blockmay.brooksdistributor.onclicks.AvailableOrdersClicks
import com.blockmay.brooksdistributor.repositories.DistributionDataRepositories
import com.blockmay.brooksdistributor.repositories.OrderRepositories
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class OrdersFragment : BaseFragment<OrderViewModel, FragmentOrdersBinding,
        OrderRepositories>() {


    var size = ""
    var phoneNumber = ""
    var quantity = 0
    var address = ""
    var amount = 0
    var token = ""
    var firstName = ""
    var lastName = ""
    var payment = ""

    var id = ""

    var notificationToken = ""


    var notiToken = ""


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        notiToken = runBlocking { userPreference.notiToken.first().toString() }





        if (TextUtils.isEmpty(notiToken) || notiToken == "null") {

            getUserNotificationToken()
        }


        viewModel.available("pending")
        viewModel.availableOrdersResponse.observe(viewLifecycleOwner){

            when(it){

                is Resource.Success ->{


                    binding.availableOrdersProgress.visibility = View.INVISIBLE

                    val adapter = AvailableOrdersAdapter(AvailableOrdersClicks {

                        firstName = it.user.firstName
                        lastName = it.user.lastName
                        size = it.size
                        quantity = it.quantity
                        phoneNumber = it.user.phone
                        address = it.address
                        id  = it.id
                        payment = it.payment_method

                        var name = "$firstName $lastName"


                        Toast.makeText(requireContext(), "${it.payment_method}", Toast.LENGTH_SHORT).show()

                        val directions = OrdersFdragmentDirections.actionOrdersFragmentToDistributionDataFragment(
                            name,
                            size.toString(),
                            quantity.toString(),
                            phoneNumber,
                            address,
                            id,
                            payment
                        )
                        findNavController().navigate(directions)


                    })


                    val list = it.value.orders.data
                    adapter.submitList(list)
                    binding.availableOrderRecycler.adapter = adapter

                }
                is Resource.Error ->{

                    binding.availableOrdersProgress.visibility = View.INVISIBLE


                }
                is Resource.Loading ->{

                    binding.availableOrdersProgress.visibility = View.VISIBLE
                }
            }
        }



        // uploading user token
        viewModel.addTokenResponse.observe(viewLifecycleOwner){

            when(it){

                is Resource.Success ->{

                    binding.availableOrdersProgress.visibility = View.INVISIBLE

//                    viewModel.saveEmail(it.value.user.email)
                    viewModel.notiToken(notificationToken!!)

                    Toast.makeText(requireContext(), "token added", Toast.LENGTH_SHORT).show()

                }

                is Resource.Error ->{
                    binding.availableOrdersProgress.visibility = View.INVISIBLE

//                    if (it.errorCode.toString() == "422"){
//                        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
//                    }
//                    else if (it.errorCode.toString() == "409"){
//                        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
//
//                    }
//                    else {
//
//                        Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
//
//
//                    }

                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

                }
                is Resource.Loading ->{

                    binding.availableOrdersProgress.visibility = View.VISIBLE

                }
            }
        }


    }


    private fun getUserNotificationToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
//                textview.text = "Fetching FCM registration token failed"
                return@OnCompleteListener
            }

            // fetching the token
            notificationToken = task.result

//            Log.d(TAG, "getUserNotificationToken: $notificationToken")


            viewModel.addToken(
                AddTokenModel(
                    notificationToken!!

                )
            )

        })
    }

    override fun getViewModel() = OrderViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOrdersBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): OrderRepositories {

        token = runBlocking { userPreference.authToken.first().toString() }

        val api = remoteDataSource.userDetails(Api::class.java, token)

        return OrderRepositories(api, userPreference)

    }}