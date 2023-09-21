package com.example.crud_second_tour.service;

import com.example.crud_second_tour.entities.User;

public interface UserService {
    User getUsernameFromToken(String token);
}
