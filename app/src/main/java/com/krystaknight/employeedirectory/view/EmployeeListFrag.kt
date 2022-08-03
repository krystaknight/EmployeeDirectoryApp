package com.krystaknight.employeedirectory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.krystaknight.employeedirectory.R
import com.krystaknight.employeedirectory.viewmodel.EmployeeListViewModel

class EmployeeListFrag : Fragment() {

    companion object {
        fun newInstance() = EmployeeListFrag()
    }

    private val viewModel: EmployeeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}