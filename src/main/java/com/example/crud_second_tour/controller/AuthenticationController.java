package com.example.crud_second_tour.controller;

import com.example.crud_second_tour.dto.authentication.AuthenticationRequest;
import com.example.crud_second_tour.dto.employee.EmployeeRequest;
import com.example.crud_second_tour.dto.organization.OrganizationRequest;
import com.example.crud_second_tour.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class AuthenticationController {
    //ВСЕ ЕНДПОИНТЫ РАБОТАЕТ С ТОКЕНОМ



    private final AuthenticationService service;
    // регистрация организаций
    @PostMapping("/register/organization")
    public ResponseEntity<?> organizationRegister(@RequestBody OrganizationRequest request) {
        return service.organizationRegister(request);
    }
    //регистрация сотрудника
    @PostMapping("/register/emp")
    public ResponseEntity<?> employerRegister(@RequestBody EmployeeRequest request) {
        return service.employerRegister(request);
    }

    //авторизация
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
