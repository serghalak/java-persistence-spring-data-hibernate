package com.manning.javapersistence.springdatajpa.model;

public interface EmployeeDepartmentView {
    String getFirstName();           // Поле з таблиці Employee
    String getEmail();       // Поле з таблиці Employee
    DepartmentView getDepartment(); // Вкладена проекція (таблиця Department)

    interface DepartmentView {
        String getName(); // Поле з таблиці Department
    }
}
