package com.krystaknight.employeedirectory.viewmodel

import com.krystaknight.employeedirectory.api.EmployeeListService
import com.krystaknight.employeedirectory.model.Employee
import com.krystaknight.employeedirectory.model.EmployeeList
import com.krystaknight.employeedirectory.model.EmployeeType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class EmployeeListViewModelTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val employee1 = Employee("01234",
        "Steven Johnson",
        "5552586931",
        "steven@gmail.com",
        "Biography here",
        "",
        "",
        "The Best Team",
        EmployeeType.CONTRACTOR)
    private val employee2 = Employee("7894",
        "Miranda Jones",
        "5554561234",
        "jones@gmail.com",
        "Another Biography here",
        "",
        "",
        "The Worst Team",
        EmployeeType.PART_TIME)
    private val employee3 = Employee("56323163",
        "Randy Smith",
        "5554568956",
        "rsmith@gmail.com",
        "Some more interesting info",
        "",
        "",
        "The Okay Team",
        EmployeeType.FULL_TIME)
    private val validJsonString: String? = "{\n" +
            "\t\"employees\" : [\n" +
            "\t\t{\n" +
            "      \"uuid\" : \"0d8fcc12-4d0c-425c-8355-390b312b909c\",\n" +
            "\n" +
            "      \"full_name\" : \"Justine Mason\",\n" +
            "      \"phone_number\" : \"5553280123\",\n" +
            "      \"email_address\" : \"jmason.demo@squareup.com\",\n" +
            "      \"biography\" : \"Engineer on the Point of Sale team.\",\n" +
            "\n" +
            "      \"photo_url_small\" : \"https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg\",\n" +
            "      \"photo_url_large\" : \"https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg\",\n" +
            "\n" +
            "      \"team\" : \"Point of Sale\",\n" +
            "      \"employee_type\" : \"FULL_TIME\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"uuid\" : \"a98f8a2e-c975-4ba3-8b35-01f719e7de2d\",\n" +
            "\n" +
            "      \"full_name\" : \"Camille Rogers\",\n" +
            "      \"phone_number\" : \"5558531970\",\n" +
            "      \"email_address\" : \"crogers.demo@squareup.com\",\n" +
            "      \"biography\" : \"Designer on the web marketing team.\",\n" +
            "\n" +
            "      \"photo_url_small\" : \"https://s3.amazonaws.com/sq-mobile-interview/photos/5095a907-abc9-4734-8d1e-0eeb2506bfa8/small.jpg\",\n" +
            "      \"photo_url_large\" : \"https://s3.amazonaws.com/sq-mobile-interview/photos/5095a907-abc9-4734-8d1e-0eeb2506bfa8/large.jpg\",\n" +
            "\n" +
            "      \"team\" : \"Public Web & Marketing\",\n" +
            "      \"employee_type\" : \"PART_TIME\"\n" +
            "    }]}"



    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
    }


    @Test
    fun getEmployeesWithValidDataTest(){
        val model = EmployeeListViewModel()
        val service = mockk<EmployeeListService>()
        val employeeList = EmployeeList(listOf(employee1,employee2,employee3))

        coEvery { service.fetchEmployees() } returns validJsonString

        model.request()
        assertEquals(model.getEmployeeData(), employeeList)
    }

    @Test
    fun getEmployeesWithMalformedJsonTest(){
        val model = EmployeeListViewModel()
        val service = mockk<EmployeeListService>()
        coEvery { service.fetchEmployees() } returns "{}"

        model.request()
        assertEquals(model.getEmployeeData().value, null)
    }

    @Test
    fun getEmployeesWithEmptyJsonTest(){
        val model = EmployeeListViewModel()
        val service = mockk<EmployeeListService>()
        val emptyJson: String? = "{\n" +
                "\t\"employees\" : [   \n" +
                "\t]\n" +
                "}"
        coEvery { service.fetchEmployees() } returns emptyJson
        model.request()
        assertEquals(model.getEmployeeData().value, null)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

}