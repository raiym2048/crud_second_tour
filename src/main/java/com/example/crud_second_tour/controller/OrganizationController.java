package com.example.crud_second_tour.controller;

import com.example.crud_second_tour.dto.organization.OrganizationRequest;
import com.example.crud_second_tour.dto.organization.OrganizationRequests;
import com.example.crud_second_tour.dto.organization.OrganizationResponses;
import com.example.crud_second_tour.entities.Organization;
import com.example.crud_second_tour.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/organization")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrganizationController {
    //ВСЕ ЕНДПОИНТЫ РАБОТАЕТ С ТОКЕНОМ
    //НА КАКИЕ РОЛИ ЕСТЬ ДОСТУП К ОПРЕДЕЛЕННЫМИ ЕНДПОИНТАМИ НАПИСАНЫ В @PreAuthorize


    private final OrganizationService organizationService;

    //список всех организаций
    @PreAuthorize("hasAnyAuthority('ORGANIZATION', 'ADMIN')")
    @GetMapping("/getAllOrganizations")
    public List<OrganizationResponses> getAllOrganizations(){
        return organizationService.getAllOrganizations();
    }
    //обновление организации
    @PreAuthorize("hasAnyAuthority('ORGANIZATION', 'ADMIN')")
    @PostMapping("/update/organization")
    public void update(@RequestBody(required = false) OrganizationRequests organizationRequests,
                       @RequestHeader("Authorization") String token){
        organizationService.update(organizationRequests, token);
    }
    //удаление организаций
    @PreAuthorize("hasAnyAuthority('ORGANIZATION', 'ADMIN')")
    @DeleteMapping("/delete")
    public void delete(@RequestParam(required = false) Long id, @RequestHeader("Authorization") String token){
        organizationService.delete(id, token);
    }


}
