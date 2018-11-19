package com.test.testclientslist.data.repositories.login

import com.test.testclientslist.data.networking.APIController
import com.test.testclientslist.data.networking.ServiceVolley
import com.test.testclientslist.utils.Contants
import org.json.JSONObject

class LoginRepository {
    private var serviceVolley = ServiceVolley()
    private var apiController = APIController(serviceVolley)

    fun tryLogin(email: String, pass: String, onReadyCallBack: onRepositoryCallBack){
        var url = String.format("%s?email=%s&password=%s", Contants.LOGIN, email, pass)
        apiController.get(url, ""){ response ->
            if(response != null){
                onReadyCallBack.onDataReady(response)
            }
        }

    }
}

interface onRepositoryCallBack{
    fun onDataReady(response: JSONObject)
}