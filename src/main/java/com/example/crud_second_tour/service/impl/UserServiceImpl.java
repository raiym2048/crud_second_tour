package com.example.crud_second_tour.service.impl;

import com.example.crud_second_tour.entities.User;
import com.example.crud_second_tour.repository.UserRepository;
import com.example.crud_second_tour.service.UserService;
import net.minidev.json.JSONObject;
import lombok.AllArgsConstructor;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getUsernameFromToken(String token) {

        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) jsonParser.parse(decoder.decode(chunks[1]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() -> new RuntimeException("user can be null, invalid token"));
    }
}
