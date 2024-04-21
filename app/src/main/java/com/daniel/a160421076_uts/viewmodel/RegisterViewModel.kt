package com.daniel.a160421076_uts.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniel.a160421076_uts.util.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(){
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> = _registerResult

    // nanti API yang akan periksa password == confPassword, karena itu perlu tetap diterima di method kotlin ini. Tapi nantinya API tidak menyimpan confPassword ke mysql
    fun register(username: String, namaDepan: String, namaBelakang: String, email: String, password: String, confPassword: String) {
        // Lakukan validasi field
        if (username.isEmpty() || namaDepan.isEmpty() || email.isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
            _registerResult.value = false
            return
        }

        if (password != confPassword) {
            _registerResult.value = false
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.service.register(username, namaDepan, namaBelakang, email, password, confPassword).execute()
            if (response.isSuccessful) {
                val responseBody = response.body()
                _registerResult.postValue(responseBody == "success")
            } else {
                _registerResult.postValue(false)
            }
        }
    }
}