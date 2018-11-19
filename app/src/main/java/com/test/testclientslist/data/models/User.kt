package com.test.testclientslist.data.models

import android.text.TextUtils
import android.util.Patterns

class User(userName: String, password: String) {
    var user = ""
    var pass = ""

    init {
        user = userName
        pass = password
    }

    fun isInputValid(): Boolean{
        if(!TextUtils.isEmpty(user) && Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            return true
        }

        return false
    }
}