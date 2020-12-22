package com.backend.magicarpe.security.services;

import com.backend.magicarpe.exception.IdNotFoundException;
import com.backend.magicarpe.repositories.UserRepository;
import com.backend.magicarpe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public UserDetails loadUserById(String id) throws IdNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("User not found with this id."));

        return UserDetailsImpl.build(user);
    }

}