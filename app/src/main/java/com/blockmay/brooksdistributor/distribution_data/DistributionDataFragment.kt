package com.blockmay.brooksdistributor.distribution_data

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.blockmay.brooksdistributor.base.BaseFragment
import com.blockmay.brooksdistributor.databinding.FragmentDistributionDataBinding
import com.blockmay.brooksdistributor.network.Resource
import com.blockmay.brooksdistributor.network.api.Api
import com.blockmay.brooksdistributor.repositories.OrderRepositories
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class DistributionDataFragment : BaseFragment<OrderViewModel, FragmentDistributionDataBinding,
        OrderRepositories>() {


    private val args: DistributionDataFragmentArgs by navArgs()


    var token = ""
    var address = ""
    var name = ""
    var size = ""
    var quantity = ""
    var phoneNumber = ""
    var longitude = ""
    var latitude = ""
    var payment = ""

    var id = ""


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        name = args.name
        size = args.size
        quantity = args.quantity
        phoneNumber = args.phoneNumber
        address = args.address
        id = args.id
        payment = args.payment

        binding.textName.text = name
        binding.textSize.text = size
        binding.textQuantity.text = quantity
        binding.textPhone.text = phoneNumber
        binding.textAddress.text = address
        binding.textPayment.text = payment

        val str = "$address"
        val delimiter = ","

        val temp = str.split(delimiter).toTypedArray()
        latitude  = temp[0]
         longitude = temp[1]

        print(name)

        binding.openMapButton.setOnClickListener {

            Toast.makeText(requireContext(), "$latitude", Toast.LENGTH_SHORT).show()
            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", "$latitude " + "," + "$longitude")
            val url = builder.build().toString()
            Log.d("Directions", url)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)


            viewModel.orderStatusUpdater(id)
        }


        viewModel.orderStatusUpdateResponse.observe(viewLifecycleOwner){

            when(it){

                is Resource.Success ->{

                    Toast.makeText(requireContext(), "Order Started", Toast.LENGTH_LONG).show()
                }

                is Resource.Error ->{

                    if (it.isNetWorkError){
                        Toast.makeText(requireContext(), "Poor or no internet connection", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        Toast.makeText(requireContext(), "order completion not registered try again", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading ->{

                }
            }
        }


        viewModel.orderStatusResponse.observe(viewLifecycleOwner){

            when(it){

                is Resource.Success ->{

                    Toast.makeText(requireContext(), "Order Completed Successfully", Toast.LENGTH_LONG).show()
                }

                is Resource.Error ->{

                    if (it.isNetWorkError){
                        Toast.makeText(requireContext(), "Poor or no internet connection", Toast.LENGTH_SHORT).show()
                    }
                    else{

                        Toast.makeText(requireContext(), "order completion not registered try again", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading ->{

                }
            }
        }


        binding.acceptOrder.setOnClickListener {

            viewModel.orderStatusUpdate(
                id
            )
        }


    }



    override fun getViewModel() = OrderViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDistributionDataBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): OrderRepositories {

        token = runBlocking { userPreference.authToken.first().toString() }

        val api = remoteDataSource.userDetails(Api::class.java, token)

        return OrderRepositories(api, userPreference)

    }

}