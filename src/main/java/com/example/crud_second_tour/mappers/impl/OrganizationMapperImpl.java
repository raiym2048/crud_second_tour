package com.example.crud_second_tour.mappers.impl;

import com.example.crud_second_tour.dto.organization.OrganizationRequests;
import com.example.crud_second_tour.dto.organization.OrganizationResponses;
import com.example.crud_second_tour.entities.Employee;
import com.example.crud_second_tour.entities.Organization;
import com.example.crud_second_tour.mappers.EmployeeMapper;
import com.example.crud_second_tour.mappers.OrganizationMapper;
import com.example.crud_second_tour.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class OrganizationMapperImpl implements OrganizationMapper {
    private final EmployeeMapper employeeMapper;
    @Override
    public List<OrganizationResponses> toDtos(List<Organization> all) {
        List<OrganizationResponses> organizationResponses = new ArrayList<>();
        for(Organization organization: all){
            organizationResponses.add(toDto(organization));
        }
        return organizationResponses;
    }

    @Override
    public Organization toEntity(OrganizationRequests organizationRequests) {
        Organization organization = new Organization();
        organization.setAddress(organizationRequests.getAddress());
        organization.setEmail(organizationRequests.getEmail());
        organization.setFax(organizationRequests.getFax());
        organization.setAttachedFiles(organizationRequests.getAttachedFiles());
        organization.setOrganizationName(organizationRequests.getOrganizationName());
        organization.setHeadName(organizationRequests.getHeadName());
        organization.setLegalForm(organizationRequests.getLegalForm());
        organization.setPhone(organizationRequests.getPhone());
        organization.setCertificateNumber(organizationRequests.getCertificateNumber());
        organization.setWebPage(organizationRequests.getWebPage());
        organization.setOwnershipType(organizationRequests.getOwnershipType());
        organization.setCertificateAcquisitionDate(organizationRequests.getCertificateAcquisitionDate());
        organization.setLicenseNumber(organizationRequests.getLicenseNumber());
        organization.setLicenseAcquisitionDate(organizationRequests.getLicenseAcquisitionDate());
        organization.setLogin(organizationRequests.getLogin());
        return organization;
    }

    private OrganizationResponses toDto(Organization organization) {
        OrganizationResponses organizationResponses = new OrganizationResponses();
        organizationResponses.setAddress(organization.getAddress());
        organizationResponses.setId(organization.getId());
        organizationResponses.setEmail(organization.getEmail());
        organizationResponses.setFax(organization.getFax());
        organizationResponses.setAttachedFiles(organization.getAttachedFiles());
        organizationResponses.setOrganizationName(organization.getOrganizationName());
        organizationResponses.setHeadName(organization.getHeadName());
        organizationResponses.setLegalForm(organization.getLegalForm());
        organizationResponses.setPhone(organization.getPhone());
        organizationResponses.setCertificateNumber(organization.getCertificateNumber());
        organizationResponses.setWebPage(organization.getWebPage());
        organizationResponses.setOwnershipType(organization.getOwnershipType());
        organizationResponses.setCertificateAcquisitionDate(organization.getCertificateAcquisitionDate());

        organizationResponses.setEmployeeResponses(employeeMapper.toDtos(organization.getEmployeeList()));
        return organizationResponses;
    }
}
