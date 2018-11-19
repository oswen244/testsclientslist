package com.test.testclientslist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.test.testclientslist.data.models.User
import com.test.testclientslist.data.repositories.login.LoginRepository
import com.test.testclientslist.data.repositories.login.onRepositoryCallBack
import com.test.testclientslist.utils.Event
import org.json.JSONObject

class LoginViewModel(application: Application): AndroidViewModel(application) {

    var loginRepository: LoginRepository = LoginRepository()

    var errorText = ObservableField("")
    var error = ObservableField(false)
    var isLoading = ObservableField(false)

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    private val _navigateToList = MutableLiveData<Event<String>>()

    val navigateToList : LiveData<Event<String>>
        get() = _navigateToList


    fun onLoginButtonClick(){
        isLoading.set(true)

        var user = User(email.value!!, password.value!!)

        if (user.isInputValid()){
            loginRepository.tryLogin(user.user, user.pass, object :
                onRepositoryCallBack {
                override fun onDataReady(response: JSONObject) {
                    isLoading.set(false)
                    if(!response.isNull("error")){
                        error.set(true)
                        errorText.set(response.getString("error"))
                    }else{
                        _navigateToList.value = Event(response.getString("authToken"))
                    }
                }

            })
        }else{
            isLoading.set(false)
            error.set(true)
            errorText.set("Por favor verifique sus credenciales de ingreso")
        }
    }

}