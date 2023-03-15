package com.blockmay.brooksdistributor.base
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blockmay.brooksdistributor.distribution_data.DistributionDataViewModel
import com.blockmay.brooksdistributor.distribution_data.OrderViewModel
import com.blockmay.brooksdistributor.repositories.BaseRepository
import com.blockmay.brooksdistributor.repositories.DistributionDataRepositories
import com.blockmay.brooksdistributor.repositories.OrderRepositories
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  return when {
            modelClass.isAssignableFrom(DistributionDataViewModel::class.java) -> DistributionDataViewModel(repository as DistributionDataRepositories) as T

            modelClass.isAssignableFrom(OrderViewModel::class.java) -> OrderViewModel(repository as OrderRepositories) as T
//
//            modelClass.isAssignableFrom(BuyDataViewModel::class.java) -> BuyDataViewModel(repository as DataRepository) as T
//
//            modelClass.isAssignableFrom(HomeViewModel::class.java)-> HomeViewModel(repository as GeneralRepository) as T
//
//            modelClass.isAssignableFrom(VerifyMeterViewModel::class.java)-> VerifyMeterViewModel(repository as MeterVerificationRepository) as T
////
//            modelClass.isAssignableFrom(HistoryViewModel::class.java)-> HistoryViewModel(repository as GeneralRepository) as T
////
//            modelClass.isAssignableFrom(MeViewModel::class.java)-> MeViewModel(repository as GeneralRepository) as T
////
//            modelClass.isAssignableFrom(AuthViewModel::class.java)-> AuthViewModel(repository as AuthenticationRepository) as T
//
//            modelClass.isAssignableFrom(PurchaseViewModel::class.java)-> PurchaseViewModel(repository as PurchaseRepository) as T
//
//            modelClass.isAssignableFrom(FlightViewModel::class.java)-> FlightViewModel(repository as FlightRepository) as T


            else -> throw IllegalArgumentException("ViewModelClass Not Fount")
        }
    }
}