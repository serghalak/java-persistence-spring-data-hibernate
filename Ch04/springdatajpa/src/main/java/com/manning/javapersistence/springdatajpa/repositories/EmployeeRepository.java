package com.manning.javapersistence.springdatajpa.repositories;

import com.manning.javapersistence.springdatajpa.model.Employee;
import com.manning.javapersistence.springdatajpa.model.EmployeeDepartmentDTO;
import com.manning.javapersistence.springdatajpa.model.EmployeeDepartmentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //List<Employee> findAll();

    // Отримання списку через Record (вимагає JPQL запиту через JOIN)
    @Query("SELECT new com.manning.javapersistence.springdatajpa.model.EmployeeDepartmentDTO(e.firstName, e.email, d.name) " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeDepartmentDTO> findAllEmployeeDepartmentDTO();

    @Query("SELECT e FROM Employee e JOIN FETCH e.department d")
    List<EmployeeDepartmentView> findAllEmployeeDepartmentView();

    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.department.name = :deptName")
    List<EmployeeDepartmentView> findEmployeesByDeptName(@Param("deptName") String deptName);

    // Отримання списку через Інтерфейс (працює автоматично по імені методів)
    //List<EmployeeDepartmentDTO> findByPosition(String position);
}
