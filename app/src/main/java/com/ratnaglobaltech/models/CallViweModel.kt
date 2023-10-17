package com.ratnaglobaltech.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ratnaglobaltech.apidemo.calls.APIService
import kotlinx.coroutines.launch

class CallViweModel : ViewModel() {


    private val _demoModel = MutableLiveData<DemoModel>()
    val demoModel: LiveData<DemoModel> get() = _demoModel

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchTodos(params: HashMap<String, String>) {
        viewModelScope.launch {
            try {
                // Making the API call
                val response = APIService.getInstance().getTodos(params)
                _demoModel.postValue(response)
            } catch (e: Exception) {
                // Handling exceptions, you can be more specific with error handling
                _error.postValue(e.message)
            }
        }
    }

}