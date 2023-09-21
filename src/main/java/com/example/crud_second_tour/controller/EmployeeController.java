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
@CrossOrigin(origins = "*", maxAge = 3600)

public class EmployeeController {
    private final EmployeeService employeeService;
    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN', 'ORGANIZATION')")
    @GetMapping("/employee")
    public List<EmployeeResponses> getAllEmployee(@RequestHeader("Authorization") String token){
        return employeeService.getAllEmployee();
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    @PostMapping("/update/employer")
    public void updateEmployee(@RequestBody(required = false) EmployeeRequests requests, @RequestHeader("Authorization") String token ){
        employeeService.update(requests, token);
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN', 'ORGANIZATION')")
    @DeleteMapping("/delete/employer")
    public void deleteEmployer(@RequestParam(required = false) Long id, @RequestHeader("Authorization") String token){
        employeeService.delete(id, token);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZATION')")
    @GetMapping("/organizationEmployers")
    public List<EmployeeResponses> getAllOrganizationEmployers(@RequestParam(required = false) Long id,
                                                               @RequestHeader("Authorization") String token){
        return employeeService.getAllOrganizationEmployers(id, token);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZATION')")
    @PutMapping("employeeToOrganization/{employeeId}")
    public void putEmployeeToOrganization(@PathVariable Long employeeId,
                                          @RequestParam(required = false) Long organizationId,
                                          @RequestHeader("Authorization") String token){
        employeeService.putEmployeeToOrganization(employeeId, organizationId, token);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZATION')")
    @DeleteMapping("employeeFromOrganization/{employeeId}")
    public void removeEmployeeFromOrganization(@PathVariable Long employeeId,
                                               @RequestParam(required = false) Long organizationId,
                                               @RequestHeader("Authorization") String token){
        employeeService.removeEmployeeFromOrganization(employeeId, organizationId, token);
    }






}
