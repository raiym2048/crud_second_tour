package com.example.crud_second_tour.dto.employee;

import com.example.crud_second_tour.entities.Organization;
import com.example.crud_second_tour.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
public class EmployeeResponses {
    private Long id;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String homeAddress;
    private String webPage;
    private String bankDetails;
    private String position;

    private Long organizationId;

}
