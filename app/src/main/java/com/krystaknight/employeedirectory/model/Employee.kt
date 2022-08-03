package com.krystaknight.employeedirectory.model

data class Employee (
    val uuid: String,
    val fullName: String,
    val phoneNumber: String?,
    val emailAddress:String,
    val biography: String?,
    val photoSmall: String?,
    val photoLarge: String?,
    val team: String,
    val employeeType: EmployeeType
    )