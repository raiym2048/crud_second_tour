package com.example.crud_second_tour.service.impl;

import com.example.crud_second_tour.dto.employee.EmployeeResponses;
import com.example.crud_second_tour.mappers.EmployeeMapper;
import com.example.crud_second_tour.repository.EmployeeRepository;
import com.example.crud_second_tour.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeResponses> getAllEmployee() {
        return employeeMapper.toDtos(employeeRepository.findAll());
    }
}
