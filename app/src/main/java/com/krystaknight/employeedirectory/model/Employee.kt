package com.krystaknight.employeedirectory.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Employee (
    val uuid: String,
    @JsonProperty("full_name")
    val fullName: String,
    @JsonProperty("phone_number")
    val phoneNumber: String?,
    @JsonProperty("email_address")
    val emailAddress:String,
    val biography: String?,
    @JsonProperty("photo_url_small")
    val photoSmall: String?,
    @JsonProperty("photo_url_large")
    val photoLarge: String?,
    val team: String,
    @JsonProperty("employee_type")
    val employeeType: EmployeeType
    )

data class EmployeeList(
    val employees: List<Employee>?
)