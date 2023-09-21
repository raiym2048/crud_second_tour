package com.example.crud_second_tour.mappers;

import com.example.crud_second_tour.dto.organization.OrganizationRequests;
import com.example.crud_second_tour.dto.organization.OrganizationResponses;
import com.example.crud_second_tour.entities.Organization;

import java.util.List;

public interface OrganizationMapper {
    List<OrganizationResponses> toDtos(List<Organization> all);

    Organization toEntity(OrganizationRequests organizationRequests);
}
