package com.daniel.a160421076_uts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniel.a160421076_uts.util.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.service.login(username, password).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                _loginResult.postValue(responseBody == "success")
            } else {
                _loginResult.postValue(false)
            }
        }
    }
}