package com.example.crud_second_tour.service;

import com.example.crud_second_tour.dto.organization.OrganizationRequests;
import com.example.crud_second_tour.dto.organization.OrganizationResponses;

import java.util.List;

public interface OrganizationService {
    List<OrganizationResponses> getAllOrganizations();

    void update(OrganizationRequests organizationRequests, String token);

    void delete(Long id, String token);
}
