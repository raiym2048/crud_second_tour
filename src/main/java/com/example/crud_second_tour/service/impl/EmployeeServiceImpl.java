package com.example.crud_second_tour.service.impl;

import com.example.crud_second_tour.dto.employee.EmployeeRequests;
import com.example.crud_second_tour.dto.employee.EmployeeResponses;
import com.example.crud_second_tour.entities.Employee;
import com.example.crud_second_tour.entities.Organization;
import com.example.crud_second_tour.entities.User;
import com.example.crud_second_tour.enums.Role;
import com.example.crud_second_tour.mappers.EmployeeMapper;
import com.example.crud_second_tour.repository.EmployeeRepository;
import com.example.crud_second_tour.repository.OrganizationRepository;
import com.example.crud_second_tour.repository.UserRepository;
import com.example.crud_second_tour.service.EmployeeService;
import com.example.crud_second_tour.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    @Override
    public List<EmployeeResponses> getAllEmployee() {
        return employeeMapper.toDtos(employeeRepository.findAll());
    }

    @Override
    public void update(EmployeeRequests requests, String token) {


        User user = userService.getUsernameFromToken(token);
        Employee employee = employeeRepository.findById(user.getEmployee().getId()).get();
        System.out.println("id of employee: "+employee.getId());
        employee.setBankDetails(requests.getBankDetails());
        employee.setPosition(requests.getPosition());
        employee.setHomeAddress(requests.getHomeAddress());
        employee.setHomePhone(requests.getHomePhone());
        employee.setWebPage(requests.getWebPage());
        employee.setWorkPhone(requests.getWorkPhone());
        employee.setMobilePhone(requests.getMobilePhone());
        Optional<Organization> organization = organizationRepository.findById(requests.getOrganizationId());

        employee.setOrganization(organization.isEmpty()?null:organization.get());
        employeeRepository.save(employee);

    }

    @Override
    public void delete(Long id, String token) {
        User user = userService.getUsernameFromToken(token);
        if (user.getRole().equals(Role.EMPLOYEE)){
            userRepository.delete(user);
        }
        else {
            if (id==null)
                throw new BadCredentialsException("the id of employer is null! we dont know which one we will delete");
            else {
                Optional<Employee> employee = employeeRepository.findById(id);
                if (employee.isEmpty())
                    throw new BadCredentialsException("we have not employee with this id!");
                employeeRepository.deleteById(id);
            }
        }

    }
}
