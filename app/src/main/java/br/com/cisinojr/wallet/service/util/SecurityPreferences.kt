package br.com.cisinojr.wallet.service.util

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("wallet", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        sharedPreferences.edit().putString(key,value).apply()
    }

    fun getStoredString(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    fun removeStoredString(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

}
