package com.blockmay.brooksdistributor.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {



    private val applicationContext = context.applicationContext

    private val dataStore: DataStore<androidx.datastore.preferences.Preferences>

    init {

        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )


    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { prefereces ->
        prefereces[KEY_AUTH]
    }

    val userId: Flow<String?>
        get() = dataStore.data.map { preference->
        preference[FLIGHT_ID]
    }

    val flightName: Flow<String?>
        get() = dataStore.data.map { preference->
        preference[FLIGHT_NAME]
    }

    val firstName: Flow<String?>
        get() = dataStore.data.map { preference->
            preference[FIRST_NAME]
        }

    val lastName: Flow<String?>
        get() = dataStore.data.map { preference->
            preference[LAST_NAME]
        }


//    suspend fun safeAuthToken(authToken: String, firstName: String, lastName: String, phoneNumber: String){
//        dataStore.edit { preference ->
//
//            preference[KEY_AUTH] = authToken
//        }
//
//    }

    suspend fun safeAuthToken(authToken: String){
        dataStore.edit { preference ->

            preference[KEY_AUTH] = authToken
        }

    }

    suspend fun saveFlightId(flightId: String){
        dataStore.edit { preference ->

            preference[FLIGHT_ID] = flightId
        }

    }

    suspend fun saveFlightName(flightName: String){
        dataStore.edit { preference ->

            preference[FLIGHT_NAME] = flightName
        }

    }

    suspend fun saveFlightNAme(flightId: String){
        dataStore.edit { preference ->

            preference[FLIGHT_ID] = flightId
        }

    }

    suspend fun saveUserDetails(firstName: String,lastName: String){
        dataStore.edit { preference ->

            preference[FIRST_NAME] = firstName
            preference[LAST_NAME] = lastName
        }

    }





    companion object{

        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val FIRST_NAME = preferencesKey<String>("first_name")
        private val LAST_NAME = preferencesKey<String>("last_name")
        private val FLIGHT_ID = preferencesKey<String>("flight_id")
        private val FLIGHT_NAME = preferencesKey<String>("flight_name")





    }
}