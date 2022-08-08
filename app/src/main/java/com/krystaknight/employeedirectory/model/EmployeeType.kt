package com.krystaknight.employeedirectory.model

enum class EmployeeType {
    FULL_TIME{
        override fun toString(): String {
            return "Full-Time"
        }
             },
    PART_TIME{
        override fun toString(): String {
            return "Part-Time"
        }
    },
    CONTRACTOR{
        override fun toString(): String {
            return "Contractor"
        }
    };
}