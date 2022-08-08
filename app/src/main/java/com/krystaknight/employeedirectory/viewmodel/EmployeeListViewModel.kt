package com.krystaknight.employeedirectory.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.krystaknight.employeedirectory.api.EmployeeListService
import com.krystaknight.employeedirectory.model.EmployeeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeListViewModel: ViewModel() {

    private val TAG = "EMPLOYEE_VIEW_MODEL"
    private var employeeData = MutableLiveData<EmployeeList>()
    private var employeeListService = EmployeeListService()

    init {
        request()
    }

    fun getEmployeeData(): LiveData<EmployeeList> = employeeData

    fun request(){
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO){
                employeeListService.fetchEmployees()
            }
            employeeData.value = parseData(list)
        }
    }

    private fun parseData(data: String?): EmployeeList?{
        if(data == null) return null
        val mapper =  jacksonObjectMapper()
        var  employeeList: EmployeeList? = null
        try {
           employeeList = mapper.readValue<EmployeeList>(data)
        } catch (err: MissingKotlinParameterException){
            Log.e(TAG, "Data malformed: ${err.message.toString()}")
        }
        return employeeList
    }
}