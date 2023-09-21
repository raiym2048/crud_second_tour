package com.example.crud_second_tour.service.impl;

import com.example.crud_second_tour.dto.authentication.AuthenticationRequest;
import com.example.crud_second_tour.dto.authentication.AuthenticationResponse;
import com.example.crud_second_tour.dto.employee.EmployeeRequest;
import com.example.crud_second_tour.dto.organization.OrganizationRequest;
import com.example.crud_second_tour.dto.user.UserResponse;
import com.example.crud_second_tour.entities.Employee;
import com.example.crud_second_tour.entities.Organization;
import com.example.crud_second_tour.entities.User;
import com.example.crud_second_tour.enums.Role;
import com.example.crud_second_tour.repository.EmployeeRepository;
import com.example.crud_second_tour.repository.OrganizationRepository;
import com.example.crud_second_tour.repository.UserRepository;
import com.example.crud_second_tour.security.JwtTokenProvider;
import com.example.crud_second_tour.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmployeeRepository employerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthenticationResponse> employerRegister(EmployeeRequest request) {
        User user = new User();
        if (request.getEmail().contains("@")) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        Employee employer = new Employee();
        employer.setEmail(user.getEmail());
        user.setEmployee(employer);
        employerRepository.save(employer);

        user.setRole(Role.EMPLOYEE);

        userRepository.save(user);
        return ResponseEntity.ok(convertAuthentication(user));
    }

    @Override
    public ResponseEntity<?> organizationRegister(OrganizationRequest request) {
        User user = new User();
        if (request.getEmail().contains("@")) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        Organization organization = new Organization();
        organization.setEmail(user.getEmail());
        user.setOrganization(organization);
        organizationRepository.save(organization);

        user.setRole(Role.ORGANIZATION);

        userRepository.save(user);
        return ResponseEntity.ok(convertAuthentication(user));
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<User> optionalAuth = userRepository.findByEmail(request.getEmail());
        if (optionalAuth.isEmpty()) {
            throw new BadCredentialsException ("User not found with email: " + request.getEmail());
        }

        User auth = optionalAuth.get();

        userRepository.save(auth);


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()));
        } catch (AuthenticationException e) {
            // Обработка ошибки аутентификации, например, неверный email или пароль
            throw new BadCredentialsException("Authentication failed: " + e.getMessage() + request.getEmail());
        }

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException("User not found"));
        String token = jwtTokenProvider.createToken(user.getEmail(), userRepository.findByEmail(user.getEmail()).get().getRole());


        return AuthenticationResponse.builder()
                .user(convertToResponse(user))
                .accessToken(token)
                .build();
    }


    private AuthenticationResponse convertAuthentication(User user) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUser(convertToResponse(user));
        String token = jwtTokenProvider.createToken(user.getEmail(), userRepository.findByEmail(user.getEmail()).get().getRole());
        response.setAccessToken(token);
        return response;
    }
    private UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        return userResponse;
    }

}
