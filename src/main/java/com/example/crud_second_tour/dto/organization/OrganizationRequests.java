package com.example.crud_second_tour.dto.organization;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganizationRequests {

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
}
