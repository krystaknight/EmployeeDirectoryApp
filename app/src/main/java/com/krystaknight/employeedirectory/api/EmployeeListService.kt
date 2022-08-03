package com.krystaknight.employeedirectory.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.krystaknight.employeedirectory.model.Employee
import com.krystaknight.employeedirectory.viewmodel.EmployeeListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.URL

class EmployeeListService(private val model: EmployeeListViewModel) {

    var client = OkHttpClient()
    private val url = "https://s3.amazonaws.com/sq-mobile-interview/employees.json"

    private fun getRequest(): String? {
        var result: String? = null
        try {
            // Create URL
            val url = URL(url)
            // Build request
            val request = Request.Builder().url(url).build()
            // Execute request
            val response = client.newCall(request).execute()
            result = response.body?.toString()
        }
        catch(err:Error) {
            Log.e("Error", "Could not receive endpoint")
        }

        return result
    }

    fun getEmployees(): String? {
        var response: String? = null
        model.viewModelScope.launch(context = Dispatchers.IO){
            response =  getRequest()
        }
        return response
    }
}