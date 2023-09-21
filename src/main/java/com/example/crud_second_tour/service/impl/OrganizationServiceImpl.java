package com.example.crud_second_tour.service.impl;

import com.example.crud_second_tour.dto.organization.OrganizationRequests;
import com.example.crud_second_tour.dto.organization.OrganizationResponses;
import com.example.crud_second_tour.entities.Organization;
import com.example.crud_second_tour.entities.User;
import com.example.crud_second_tour.enums.Role;
import com.example.crud_second_tour.mappers.OrganizationMapper;
import com.example.crud_second_tour.repository.OrganizationRepository;
import com.example.crud_second_tour.repository.UserRepository;
import com.example.crud_second_tour.service.OrganizationService;
import com.example.crud_second_tour.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public List<OrganizationResponses> getAllOrganizations() {
        return organizationMapper.toDtos(organizationRepository.findAll());
    }

    @Override
    public void update(OrganizationRequests organizationRequests, String token) {
        User user = userService.getUsernameFromToken(token);
        if (user.getRole().equals(Role.ORGANIZATION)){
            Organization organization = organizationMapper.toEntity(organizationRequests);
            organization.setId(user.getOrganization().getId());

            organizationRepository.save(organization);
        }else {
            Organization organization = organizationMapper.toEntity(organizationRequests);
            organization.setId(organizationRequests.getId());
            organizationRepository.save(organization);
        }
    }

    @Override
    public void delete(Long id, String token) {
        User user = userService.getUsernameFromToken(token);

        if (id==null && user.getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("if id null, i cannot remove no one organization!");
        if (user.getRole().equals(Role.ADMIN)){
            Optional<Organization> organization = organizationRepository.findById(id);
            if(organization.isEmpty())
                throw new BadCredentialsException("We have not orgaanization with this id!");
             userRepository.deleteById(organization.get().getUser().getId());
        }
        else {
            userRepository.deleteById(user.getId());
        }

    }
}
