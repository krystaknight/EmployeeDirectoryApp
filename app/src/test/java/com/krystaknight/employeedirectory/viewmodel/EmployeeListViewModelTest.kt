package com.krystaknight.employeedirectory.viewmodel

import com.krystaknight.employeedirectory.model.Employee
import com.krystaknight.employeedirectory.model.EmployeeList
import com.krystaknight.employeedirectory.model.EmployeeType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class EmployeeListViewModelTest {
    private val employee1 = Employee("0d8fcc12-4d0c-425c-8355-390b312b909c",
        "Justine Mason",
        "5553280123",
        "jmason.demo@squareup.com",
        "Engineer on the Point of Sale team.",
        "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
        "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
        "Point of Sale",
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
            "    }]}"

    private val scope = TestScope()

    @Before
    fun setup(){
        Dispatchers.setMain(StandardTestDispatcher(scope.testScheduler))
    }

    @Test
    fun parseDataNull(){
        val model = EmployeeListViewModel()
        assertEquals(EmployeeList(null), model.parseData(null))
    }

    @Test
    fun parseDataValid(){
        val model = EmployeeListViewModel()
        val employeelist = EmployeeList(listOf(employee1))
        assertEquals(employeelist, model.parseData(validJsonString))
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

}