package com.feandrade.forecast.weather.data.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng

class SharedPreference(context: Context) : DataStorage {
 companion object{
     private const val CACHENAME = "WEATHERCACHE"
     const val CITY = "CITY"
     const val LAT = "LAT"
     const val LOG = "LOG"

 }
    private val sharedPref : SharedPreferences = context.getSharedPreferences(CACHENAME, Context.MODE_PRIVATE)

    override fun getData(key: String): String? =
        sharedPref.getString(key, "")

    override fun saveData(key: String, email: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, email)
        editor.apply()
    }

    override fun saveIntegerData(key: String, value: Long){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    override fun saveStringSet(key: String, data: Set<String>) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(key, data)
        editor.apply()
    }

    override fun getStringSet(key: String): Set<String>
            = sharedPref.getStringSet(key, setOf()) as Set<String>


    override fun deleteData(key: String) {
        sharedPref.edit().remove(key).apply()
    }
}