package com.example.crud_second_tour.dto.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequests {
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
