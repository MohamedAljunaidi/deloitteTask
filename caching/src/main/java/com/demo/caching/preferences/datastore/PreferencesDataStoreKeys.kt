package com.demo.caching.preferences.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesDataStoreKeys {

    /**
     * for shared preferences to data store migrations purpose
     * key name for each value should be as what it was in shared preferences
     */
    val loggedInUserFullName = stringPreferencesKey("loggedInUserFullName")

}