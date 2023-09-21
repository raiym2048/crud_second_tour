package com.example.crud_second_tour.mappers.impl;

import com.example.crud_second_tour.dto.employee.EmployeeResponses;
import com.example.crud_second_tour.entities.Employee;
import com.example.crud_second_tour.mappers.EmployeeMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    @Override
    public List<EmployeeResponses> toDtos(List<Employee> all) {

        List<EmployeeResponses> employeeResponses = new ArrayList<>();
        for(Employee employee: all){
            employeeResponses.add(toDto(employee));
        }
        return employeeResponses;
    }

    @Override
    public EmployeeResponses toDto(Employee employee) {
        EmployeeResponses employeeResponses = new EmployeeResponses();
        employeeResponses.setId(employee.getId());
        employeeResponses.setEmail(employee.getEmail());
        employeeResponses.setBankDetails(employee.getBankDetails());
        employeeResponses.setPosition(employee.getPosition());
        employeeResponses.setHomePhone(employee.getHomePhone());
        employeeResponses.setHomeAddress(employee.getHomeAddress());
        employeeResponses.setMobilePhone(employee.getMobilePhone());
        employeeResponses.setOrganizationId(employee.getOrganization()!=null? employee.getOrganization().getId() : null);

        employeeResponses.setWebPage(employee.getWebPage());

        return employeeResponses;
    }
}
