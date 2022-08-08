package com.krystaknight.employeedirectory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.krystaknight.employeedirectory.databinding.FragmentEmployeeListBinding
import com.krystaknight.employeedirectory.viewmodel.EmployeeListViewModel


class EmployeeListFrag : Fragment() {
    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!
    private val model: EmployeeListViewModel by viewModels()
    private lateinit var employeeRecyclerView: RecyclerView
    private lateinit var adapter: EmployeeListAdapter
    private lateinit var swipeToRefresh: SwipeRefreshLayout
    private lateinit var initLoadingSpinner: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        employeeRecyclerView = binding.employeeRecyclerView
        employeeRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = EmployeeListAdapter(null)
        employeeRecyclerView.adapter = adapter
        initLoadingSpinner = binding.initLoadingSpinner
        initLoadingSpinner.visibility = View.VISIBLE
        swipeToRefresh = binding.swipeToRefresh
        swipeToRefresh.setOnRefreshListener{
            model.request()
        }
    }

    override fun onResume() {
        super.onResume()
        model.getEmployeeData().observe(this) { employeeList ->
            swipeToRefresh.isRefreshing = false
            initLoadingSpinner.visibility = View.GONE
            adapter.updateEmployeeList(employeeList)
            adapter.notifyDataSetChanged()
            if (employeeList == null) {
                binding.errorView.visibility = View.VISIBLE
            } else if(employeeList.employees.isEmpty()){
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.errorView.visibility = View.GONE
                binding.emptyView.visibility = View.GONE
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}