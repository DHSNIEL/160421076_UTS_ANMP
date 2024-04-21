package com.daniel.a160421076_uts.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.daniel.a160421076_uts.model.User
import com.daniel.a160421076_uts.util.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileViewModel: ViewModel() {
    private val _userData = MutableLiveData<User?>()
    val userData: MutableLiveData<User?> = _userData

    private val _updateResult = MutableLiveData<Boolean>()
    val updateResult : LiveData<Boolean> = _updateResult

    fun getUserData(username: String){
        viewModelScope.launch(Dispatchers.IO){
            ApiClient.service.getUserData(username).enqueue(object :Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful){
                        val user = response.body()
                        _userData.postValue(user)
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    _userData.postValue(null)
                }
            })
        }

    }

    fun update(username: String, namaDepan:String, namaBelakang:String, password:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = ApiClient.service.update(username, namaDepan, namaBelakang, password).execute()
            if(response.isSuccessful){
                val responsebody = response.body()
                _updateResult.postValue(responsebody == "success")
            } else{
                _updateResult.postValue(false)
            }
        }
    }
}