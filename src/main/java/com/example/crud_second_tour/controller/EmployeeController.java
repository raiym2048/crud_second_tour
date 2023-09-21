package com.example.crud_second_tour.controller;

import com.example.crud_second_tour.dto.employee.EmployeeRequest;
import com.example.crud_second_tour.dto.employee.EmployeeRequests;
import com.example.crud_second_tour.dto.employee.EmployeeResponses;
import com.example.crud_second_tour.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/getAllEmployee")
    public List<EmployeeResponses> getAllEmployee(@RequestHeader("Authorization") String token){
        return employeeService.getAllEmployee();
    }

    @PreAuthorize("EMPLOYEE")
    @PostMapping("/update/employer")
    public EmployeeResponses updateEmployee(@RequestBody(required = false) EmployeeRequests requests, @RequestHeader("Authorization") String token ){

        return  null;
    }
}
