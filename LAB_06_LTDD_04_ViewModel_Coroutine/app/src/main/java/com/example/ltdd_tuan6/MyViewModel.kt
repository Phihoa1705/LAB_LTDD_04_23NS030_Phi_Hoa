package com.example.ltdd_tuan6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _message = MutableLiveData<Int>() // Biến có thể thay đổi
    public val message: LiveData<Int> // Biến chỉ đọc
        get() = _message

    fun updateText(v:Int) {
        _message.value = v  // Thay đổi dữ liệu
    }

    fun cong(a:Int,b:Int) {
        viewModelScope.launch {
            delay(5000L)
            _message.postValue(a + b)
        }
    }

    fun tru(a:Int,b:Int) {
        viewModelScope.launch {
            delay(5000L)
            _message.postValue(a - b)
        }
    }
}