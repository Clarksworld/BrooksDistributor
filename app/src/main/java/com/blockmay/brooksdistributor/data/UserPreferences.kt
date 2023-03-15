package com.blockmay.waterorder.data

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

    private val dataStore: DataStore<Preferences>

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

    val savePhoneNumber: Flow<String?>
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

    val notiToken: Flow<String?>
        get() = dataStore.data.map { preference->
            preference[NOTI_TOKEN]
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

    suspend fun saveEmail(email: String){
        dataStore.edit { preference ->

            preference[FLIGHT_ID] = email
        }

    }

    suspend fun saveFirstName(flightName: String){
        dataStore.edit { preference ->

            preference[FIRST_NAME] = flightName
        }

    }

    suspend fun saveLastName(flightId: String){
        dataStore.edit { preference ->

            preference[LAST_NAME] = flightId
        }

    }

    suspend fun saveUserPhone(phoneNumber: String){
        dataStore.edit { preference ->

            preference[FLIGHT_NAME] = phoneNumber
        }

    }

    suspend fun notiToken(notiToken: String){
        dataStore.edit { preference ->

            preference[NOTI_TOKEN] = notiToken
        }

    }





    companion object{

        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val FIRST_NAME = preferencesKey<String>("first_name")
        private val LAST_NAME = preferencesKey<String>("last_name")
        private val FLIGHT_ID = preferencesKey<String>("flight_id")
        private val FLIGHT_NAME = preferencesKey<String>("flight_name")
        private val NOTI_TOKEN = preferencesKey<String>("noti_token")






    }
}