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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<EmployeeResponses> getAllOrganizationEmployers(Long id, String token) {
        User user = userService.getUsernameFromToken(token);
        checkForAdminRoleAndSecondParameterId(id,user);
        if (user.getRole().equals(Role.ADMIN)){
            if (organizationRepository.findById(id).isEmpty())
                throw new BadCredentialsException("we have not organization with this id!");
            return
                    employeeMapper.toDtos
                            (organizationRepository.findById(id).get().getEmployeeList());
        }
        return employeeMapper.toDtos
                (user.getOrganization().getEmployeeList());
    }

    @Override
    public void putEmployeeToOrganization(Long employeeId, Long organizationId, String token) {
        if (employeeId==null)
            throw new BadCredentialsException("the id of employee must not be null!");
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty())
            throw new BadCredentialsException("We have not employee with this id!");

        User user = userService.getUsernameFromToken(token);
        checkForAdminRoleAndSecondParameterId(organizationId,user);
        if (user.getRole().equals(Role.ADMIN)){
            putEmployeeToOrganization(organizationId, employee);
        }
        else {
            putEmployeeToOrganization(user, employee);
        }
    }

    private void putEmployeeToOrganization(User user, Optional<Employee> employee){
        Organization organization = user.getOrganization();
        List<Employee> employees = organization.getEmployeeList();
        if (!employees.contains(employee.get()))
            employees.add(employee.get());
        organization.setEmployeeList(employees);
        organizationRepository.save(organization);
    }
    private void putEmployeeToOrganization(Long organizationId, Optional<Employee> employee){
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        if(organization.isEmpty())
            throw new BadCredentialsException("We have not organization with this id!");
        List<Employee> employees = organization.get().getEmployeeList();
        if (!employees.contains(employee.get()))
            employees.add(employee.get());
        organization.get().setEmployeeList(employees);
        organizationRepository.save(organization.get());
    }

    private void checkForAdminRoleAndSecondParameterId(Long organizationId, User user) {
        if (organizationId == null && user.getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("the id of organization is null! we dont know which one we will show");

    }
    @Override
    public void removeEmployeeFromOrganization(Long employeeId, Long organizationId, String token) {
        if (employeeId==null)
            throw new BadCredentialsException("the id of employee must not be null!");

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty())
            throw new BadCredentialsException("We have not employee with this id!");

        User user = userService.getUsernameFromToken(token);
        checkForAdminRoleAndSecondParameterId(organizationId, user);

        if (user.getRole().equals(Role.ADMIN)) {
            removeEmployeeFromOrganization(organizationId, employee);
        } else {
            removeEmployeeFromOrganization(user, employee);
        }
    }

    private void removeEmployeeFromOrganization(User user, Optional<Employee> employee) {
        Organization organization = user.getOrganization();
        List<Employee> employees = organization.getEmployeeList();
        employees.remove(employee.get());
        organizationRepository.save(organization);
    }

    private void removeEmployeeFromOrganization(Long organizationId, Optional<Employee> employee) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);
        if (organization.isEmpty())
            throw new BadCredentialsException("We have not organization with this id!");

        List<Employee> employees = organization.get().getEmployeeList();
        employees.remove(employee.get());
        organizationRepository.save(organization.get());
    }

}
