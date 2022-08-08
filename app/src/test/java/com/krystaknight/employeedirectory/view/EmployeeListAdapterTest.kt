package com.krystaknight.employeedirectory.view

import com.krystaknight.employeedirectory.model.Employee
import com.krystaknight.employeedirectory.model.EmployeeList
import com.krystaknight.employeedirectory.model.EmployeeType
import org.junit.Assert.assertEquals
import org.junit.Test

internal class EmployeeListAdapterTest {

    @Test
    fun getItemCountNullOrEmpty() {
        val employeeListAdapter = EmployeeListAdapter(null)
        assertEquals(employeeListAdapter.itemCount, 0)
    }
    @Test
    fun getItemCountTest() {
        val employeeList = EmployeeList(listOf(Employee("01234",
            "Steven Johnson",
            "5552586931",
            "steven@gmail.com",
            "Biography here",
            "",
            "",
            "The Best Team",
            EmployeeType.CONTRACTOR)))
        val employeeListAdapter = EmployeeListAdapter(employeeList)
        assertEquals(employeeListAdapter.itemCount, 1)
    }

    @Test
    fun updateEmployeeListTest() {
        val employeeListAdapter = EmployeeListAdapter(null)
        val newEmployeeList = EmployeeList(listOf(Employee("01234",
            "Steven Johnson",
            "5552586931",
            "steven@gmail.com",
            "Biography here",
            "",
            "",
            "The Best Team",
            EmployeeType.CONTRACTOR)))

        assertEquals(employeeListAdapter.itemCount, 0)

        employeeListAdapter.updateEmployeeList(newEmployeeList)
        assertEquals(employeeListAdapter.itemCount, 1)

    }
}