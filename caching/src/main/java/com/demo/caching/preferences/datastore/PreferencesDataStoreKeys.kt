package com.demo.caching.preferences.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesDataStoreKeys {

    val loggedInUserFullName = stringPreferencesKey("loggedInUserFullName")

}