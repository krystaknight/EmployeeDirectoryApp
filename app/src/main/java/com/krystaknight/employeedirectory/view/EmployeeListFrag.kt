package com.krystaknight.employeedirectory.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.krystaknight.employeedirectory.R
import com.krystaknight.employeedirectory.api.EmployeeListService
import com.krystaknight.employeedirectory.databinding.FragmentEmployeeListBinding
import com.krystaknight.employeedirectory.viewmodel.EmployeeListViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class EmployeeListFrag : Fragment() {
    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!
    private val model: EmployeeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        model.getEmployeeData().observe(this, { employeeList ->
            //todo update view here
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}