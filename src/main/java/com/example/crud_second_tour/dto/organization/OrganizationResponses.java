package com.example.crud_second_tour.dto.organization;


import com.example.crud_second_tour.dto.employee.EmployeeResponses;
import com.example.crud_second_tour.entities.Employee;
import com.example.crud_second_tour.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
public class OrganizationResponses {
    private Long id;
    private String ownershipType;
    private String legalForm;
    private String organizationName;
    private String headName;
    private String fax;
    private String email;
    private String phone;
    private String webPage;
    private String login;
    private String licenseNumber;
    private String licenseAcquisitionDate;
    private String certificateNumber;
    private String certificateAcquisitionDate;
    private String address;
    private String attachedFiles;
    private List<EmployeeResponses> employeeResponses;
}
