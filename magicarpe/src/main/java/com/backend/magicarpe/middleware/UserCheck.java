package com.backend.magicarpe.middleware;

import com.backend.magicarpe.model.User;
import com.backend.magicarpe.repositories.UserRepository;
import com.backend.magicarpe.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCheck {

    @Autowired
    private JwtUtils utils;

    @Autowired
    private UserRepository userRepository;

    public User checkIdentity(String header) {
        String token = utils.getToken(header);
        Optional<User> optionalUser = userRepository.findById(utils.getIdFromJwtToken(token));
        if(optionalUser.isPresent()) return optionalUser.get();
        else return null;
    }
}
