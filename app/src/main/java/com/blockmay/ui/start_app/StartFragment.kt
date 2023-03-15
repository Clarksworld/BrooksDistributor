package com.blockmay.waterorder.ui.start_app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import androidx.navigation.fragment.navArgs
import com.blockmay.brooksdistributor.auth.AuthActivity
import com.blockmay.brooksdistributor.base.BaseFragment
import com.blockmay.brooksdistributor.databinding.FragmentStartBinding
import com.blockmay.brooksdistributor.distribution_data.OrderViewModel
import com.blockmay.brooksdistributor.network.api.Api
import com.blockmay.brooksdistributor.repositories.OrderRepositories
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executor

class StartFragment : BaseFragment<OrderViewModel, FragmentStartBinding,
        OrderRepositories>() {

//    private val args: DepositFragmentArgs by navArgs()
//
//    lateinit var executor: Executor
//    lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
//    lateinit var prompt: androidx.biometric.BiometricPrompt.PromptInfo


    var size = ""
    var payment = ""
    var quantity = 0
    var address = ""
    var amount = 0
    var token = ""
    var firstNAme = ""

    var hasNotificationPermissionGranted = false


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        token = runBlocking { userPreference.authToken.first().toString() }


            Handler().postDelayed({




                    if (Build.VERSION.SDK_INT >= 33) {
                        notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)

                    } else {

                        hasNotificationPermissionGranted = true

                        val intent = Intent(requireContext(), AuthActivity::class.java)
                        startActivity(intent)
                        activity?.finish()

                    }

                    if (PermissionChecker.checkSelfPermission(
                            requireContext(),
                            android.Manifest.permission.POST_NOTIFICATIONS
                        ) == PermissionChecker.PERMISSION_GRANTED) {


                        val intent = Intent(requireContext(), AuthActivity::class.java)
                        startActivity(intent)
                        activity?.finish()





                }

            },2000

            )





    }



    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            hasNotificationPermissionGranted = isGranted
            if (!isGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                            showNotificationPermissionRationale()
                        } else {
                            showSettingDialog()
                        }
                    }
                }
            }else{
//                Toast.makeText(requireContext(), "permission granted", Toast.LENGTH_SHORT).show()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    activity?.finish()


            }

        }


    private fun showSettingDialog() {
        MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("Notification Permission")
            .setMessage("Notification permission is required, Please allow notification permission from setting")
            .setPositiveButton("Ok") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:${requireActivity().packageName}")
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNotificationPermissionRationale() {

        MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("Alert")
            .setMessage("Notification permission is required, to show notification")
            .setPositiveButton("Ok") { _, _ ->
                if (Build.VERSION.SDK_INT >= 33) {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }






    override fun getViewModel() = OrderViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentStartBinding.inflate(inflater, container, false)


    override fun getFragmentRepository(): OrderRepositories {

        token = runBlocking { userPreference.authToken.first().toString() }
        firstNAme = runBlocking { userPreference.firstName.first().toString() }

        val api = remoteDataSource.userDetails(Api::class.java, token)

        return OrderRepositories(api, userPreference)

    }
}