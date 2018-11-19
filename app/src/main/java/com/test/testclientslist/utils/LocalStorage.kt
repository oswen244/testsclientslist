package com.test.testclientslist.utils

import android.content.Context
import android.content.SharedPreferences

class LocalStorage(context: Context) {
    private val localstorage: SharedPreferences

    /**
     * Obtiene el token almacenado.
     */
    val token: String
        get() = localstorage.getString("token", "")


    init {
        localstorage = context.getSharedPreferences(SP_NAME, 0)
    }

    /**
     * Guarda el token
     *
     * token = token devuelto por el servidor.
     */
    fun setToken(token: String) {
        val spEditor = localstorage.edit()
        spEditor.putString("token", token)
        spEditor.apply()
    }

    /**
     * Borra todos los datos almacenados.
     */
    fun clearDataAll() {
        val spEditor = localstorage.edit()
        spEditor.clear()
        spEditor.apply()
    }

    /**
     * Borra el token almacenado.
     */
    fun clearTokenData() {
        val spEditor = localstorage.edit()
        //spEditor.clear();
        spEditor.remove("token")
        spEditor.apply()
    }

    companion object {
        private val SP_NAME = "token"
    }
}