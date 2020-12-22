package com.backend.magicarpe.payloads.response;

import com.backend.magicarpe.model.User;
import com.backend.magicarpe.security.services.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    private UserDetailsImpl user;
    private List<UserDetailsImpl> users = new ArrayList<>();

    public UserResponse(User user) {
        this.user = new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail());
    }

    public UserResponse(List<User> users) {
        for (User user: users) {
            this.users.add(new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail()));
        }
    }

    public List<UserDetailsImpl> getUsers() {
        return this.users;
    }

    public UserDetailsImpl getUser() {
        return this.user;
    }
}
