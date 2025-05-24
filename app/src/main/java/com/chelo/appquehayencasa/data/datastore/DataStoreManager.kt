package com.chelo.appquehayencasa.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("startOnboarding")
val ONBOARDING = booleanPreferencesKey(name = "onboarding")

class DataStoreManager (private val context : Context){

    val getOnboarding: Flow<Boolean>  =context.dataStore.data.map { preferences ->
        preferences[ONBOARDING] ?: false
    }

    suspend fun saveOnboarding(value: Boolean){
        context.dataStore.edit {
            it[ONBOARDING] = value
        }
    }
}