package com.krystaknight.employeedirectory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krystaknight.employeedirectory.api.EmployeeListService
import com.krystaknight.employeedirectory.model.Employee

class EmployeeListViewModel: ViewModel() {

    private var employeeData = MutableLiveData<List<Employee>>()
    private var employeeListService = EmployeeListService(this)

    init {
        request()
    }

    fun getEmployeeData(): LiveData<List<Employee>> = employeeData


    private fun request(){
        val list = employeeListService.getEmployees()
        employeeData = parseData(list)
    }

    private fun parseData(data: String?): MutableLiveData<List<Employee>>{
        if(data == null){
            return MutableLiveData<List<Employee>>()
        }
        return MutableLiveData<List<Employee>>()
        //todo: parse data from json here

    }
}