package com.example.crud_second_tour.service;

import com.example.crud_second_tour.dto.employee.EmployeeRequests;
import com.example.crud_second_tour.dto.employee.EmployeeResponses;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponses> getAllEmployee();

    void update(EmployeeRequests requests, String token);

    void delete(Long id, String token);

    List<EmployeeResponses> getAllOrganizationEmployers(Long id, String token);

    void putEmployeeToOrganization(Long employeeId, Long organizationId, String token);

    void removeEmployeeFromOrganization(Long employeeId, Long organizationId, String token);
}
