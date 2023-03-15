package com.blockmay.brooksdistributor.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.blockmay.brooksdistributor.base.BaseFragment
import com.blockmay.brooksdistributor.databinding.FragmentAuthBinding
import com.blockmay.brooksdistributor.distribution_data.DistributionDataActivity
import com.blockmay.brooksdistributor.distribution_data.OrderViewModel
import com.blockmay.brooksdistributor.network.Resource
import com.blockmay.brooksdistributor.network.api.Api
import com.blockmay.brooksdistributor.repositories.OrderRepositories
import com.blockmay.brooksdistributor.visible



class AuthFragment : BaseFragment<OrderViewModel, FragmentAuthBinding,
        OrderRepositories>(){

    var userRefCode = ""
    var name =""
    var email = ""
    var phoneNumber = ""


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.signUpProgress.visible(false)

        viewModel.userLoginResponse.observe(viewLifecycleOwner){

            binding.signUpProgress.visible(true)

            when(it){



                is Resource.Success -> {
                    binding.signUpProgress.visible(false)
                   viewModel.saveAuthToken(it.value.data.token)
                   viewModel.saveFirsName(it.value.data.firstName)
                    viewModel.saveLastName(it.value.data.lastName)
                    viewModel.saveEmail(it.value.data.email)
                    viewModel.saveUserPhone(it.value.data.phone)


                    val intent = Intent(requireContext(), DistributionDataActivity::class.java)
                    startActivity(intent)
                    activity?.finish()

               }

                is Resource.Error -> {

                    binding.signUpProgress.visible(false)

                    Toast.makeText(requireContext(), "No internet or wrong Ref code ${it.isNetWorkError}", Toast.LENGTH_SHORT).show()


                }

                is Resource.Loading ->{

                    binding.signUpProgress.visible(true)

                }

            }


        }


        binding.continueButton.setOnClickListener {

             userRefCode = binding.enterRefCodeEd.text.toString().trim()


            if (userRefCode.isEmpty()){
                Toast.makeText(requireContext(), "User must provide their Ref code inorder to proceed", Toast.LENGTH_LONG).show()
            }else{

                viewModel.registerUsers(
                        userRefCode
//                    "bebe24665"
                    )


//                val intent = Intent(requireContext(), MainActivity::class.java)
//                startActivity(intent)
//                activity?.finish()

            }

        }

        binding.requestRefCode.setOnClickListener {

//            findNavController().navigate(R.id.action_authFragment2_to_getRefFragment)

        }
    }



    override fun getViewModel() = OrderViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAuthBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = OrderRepositories(
        remoteDataSource.userLogin(Api::class.java), userPreference)



}