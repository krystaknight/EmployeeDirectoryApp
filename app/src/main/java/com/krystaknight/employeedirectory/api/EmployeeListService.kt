package com.krystaknight.employeedirectory.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class EmployeeListService {

    private var client = OkHttpClient()
    private val url = "https://s3.amazonaws.com/sq-mobile-interview/employees.json"

    fun fetchEmployees(): String? {
        var result: String? = null
        try {
            val url = URL(url)
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            Log.e("Error", err.message.toString())
        }
        return result
    }
}