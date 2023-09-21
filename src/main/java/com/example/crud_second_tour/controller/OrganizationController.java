package com.example.crud_second_tour.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/organization")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrganizationController {
}
