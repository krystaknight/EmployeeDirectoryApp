package com.krystaknight.employeedirectory.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krystaknight.employeedirectory.R
import com.krystaknight.employeedirectory.databinding.EmployeeListItemBinding
import com.krystaknight.employeedirectory.model.EmployeeList

class EmployeeListAdapter(private var employeeList: EmployeeList): RecyclerView.Adapter<EmployeeListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val binding = EmployeeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        val employees = employeeList.employees
        if(!employees.isNullOrEmpty()){
            with(holder){
                with(employees[position]){
                    binding.bio.text = biography
                    binding.email.text = emailAddress
                    binding.team.text = team
                    binding.name.text = fullName
                    binding.phone.text = formatPhoneNumber(phoneNumber)
                    binding.type.text = employeeType.toString()
                    Glide.with(binding.profilePic.context)
                        .load(photoSmall)
                        .placeholder(R.drawable.place_holder_img)
                        .error(R.drawable.image_not_found)
                        .into(binding.profilePic)
                }
            }
        }
    }

    override fun getItemCount() = employeeList.employees?.size ?: 0

    fun  updateEmployeeList(newEmployeeList: EmployeeList){
        employeeList = newEmployeeList
    }

    @VisibleForTesting
    fun formatPhoneNumber(number: String?): String {
        if(number == null) return ""
        return "(${number.take(3)}) ${number.substring(3,6)}-${number.takeLast(4)}"
    }
}