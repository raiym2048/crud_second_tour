package com.example.crud_second_tour.mappers;

import com.example.crud_second_tour.dto.employee.EmployeeResponses;
import com.example.crud_second_tour.entities.Employee;

import java.util.List;

public interface EmployeeMapper {
    List<EmployeeResponses> toDtos(List<Employee> all);

    EmployeeResponses toDto(Employee employee);
}
