package com.example.crud_second_tour.service;

import com.example.crud_second_tour.dto.authentication.AuthenticationRequest;
import com.example.crud_second_tour.dto.authentication.AuthenticationResponse;
import com.example.crud_second_tour.dto.employee.EmployeeRequest;
import com.example.crud_second_tour.dto.organization.OrganizationRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<AuthenticationResponse> employerRegister(EmployeeRequest request);

    ResponseEntity<?> organizationRegister(OrganizationRequest request);

    Object authenticate(AuthenticationRequest request);
}
