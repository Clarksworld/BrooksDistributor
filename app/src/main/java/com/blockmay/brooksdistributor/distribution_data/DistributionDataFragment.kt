package com.blockmay.brooksdistributor.distribution_data

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blockmay.brooksdistributor.base.BaseFragment
import com.blockmay.brooksdistributor.databinding.FragmentDistributionDataBinding
import com.blockmay.brooksdistributor.repositories.DistributionDataRepositories


class DistributionDataFragment : BaseFragment<DistributionDataViewModel, FragmentDistributionDataBinding,
        DistributionDataRepositories>() {




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.openMapButton.setOnClickListener {

            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", "5.1819 " + "," + "7.7148")
            val url = builder.build().toString()
            Log.d("Directions", url)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


    }



    override fun getViewModel() = DistributionDataViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDistributionDataBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = DistributionDataRepositories()


}