package com.blockmay.brooksdistributor.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.afrep.afreputility.ui.base.ViewModelFactory
import com.blockmay.brooksdistributor.data.UserPreferences
import com.blockmay.brooksdistributor.network.RemoteDataSource
import com.blockmay.brooksdistributor.repositories.BaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM: ViewModel, B: ViewBinding, R: BaseRepository>: Fragment() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()
    protected lateinit var userPreference: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        userPreference = UserPreferences(requireContext())

        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch { userPreference.authToken.first() }

        return binding.root
    }


    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R

//     fun showBottomSheetDialog() {
//
//        val dialog = BottomSheetDialog(requireContext())
//
//        dialog.setContentView(com.afrep.afreputility.R.layout.fragment_bottom_sheet)
//
//        val RLEdit = dialog.findViewById<RelativeLayout>(com.afrep.afreputility.R.id.rl_edit)
//        val RLDelete = dialog.findViewById<RelativeLayout>(com.afrep.afreputility.R.id.rl_delete)
//        val RLAdd = dialog.findViewById<RelativeLayout>(com.afrep.afreputility.R.id.rl_add)
//
//        RLEdit?.setOnClickListener {  //handle click event
//
//            Toast.makeText(requireContext(), "Perform edit operation", Toast.LENGTH_SHORT).show()
//        }
//        RLDelete?.setOnClickListener {  //handle click event
//            Toast.makeText(requireContext(), "Perform delete operation", Toast.LENGTH_SHORT).show()
//        }
//        RLAdd?.setOnClickListener {  //handle click event
//            Toast.makeText(requireContext(), "Perform add operation", Toast.LENGTH_SHORT).show()
//        }
//        dialog.show()
//    }


//    fun errorBottomSheet(errorMessage: String){
//
//        val errorDialog = BottomSheetDialog(requireContext())
//        errorDialog.setContentView(com.afrep.afreputility.R.layout.error_dialog)
//
//        val errorTxt = errorDialog.findViewById<TextView>(com.afrep.afreputility.R.id.textView50)
//        errorTxt?.text = errorMessage
//
//        val errorButton = errorDialog.findViewById<Button>(com.afrep.afreputility.R.id.dismiss)
//
//        errorButton?.setOnClickListener {
//
//            errorDialog.dismiss()
//        }
//
//        errorDialog.show()
//
//
//    }

//    fun resetPasswordBottomSheet() {
//        val rstPasswordDialog = BottomSheetDialog(requireContext())
//        rstPasswordDialog.setContentView(com.afrep.afreputility.R.layout.fragment_reset_password)
//
//        val saveButton = rstPasswordDialog.findViewById<Button>(com.afrep.afreputility.R.id.save)
//
//        saveButton?.setOnClickListener {
//
//        }
//
//        rstPasswordDialog.show()
//
//    }


//    fun successBottomSheet(successMessage: String){
//
//        val successDialog = BottomSheetDialog(requireContext())
//        successDialog.setContentView(com.afrep.afreputility.R.layout.success_dialog)
//
//        val successTxt = successDialog.findViewById<TextView>(com.afrep.afreputility.R.id.textView50)
//        successTxt?.text = successMessage
//
//        val successButton = successDialog.findViewById<Button>(com.afrep.afreputility.R.id.dismiss)
//
//        successButton?.setOnClickListener {
//
//            findNavController().navigate(com.afrep.afreputility.R.id.navigation_home)
//            successDialog.dismiss()
//        }
//
//        successDialog.show()
//
//
//    }




}
