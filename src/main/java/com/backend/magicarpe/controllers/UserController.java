package com.backend.magicarpe.controllers;

import com.backend.magicarpe.middleware.UserCheck;
import com.backend.magicarpe.model.User;
import com.backend.magicarpe.payloads.response.MessageResponse;
import com.backend.magicarpe.payloads.response.UserResponse;
import com.backend.magicarpe.repositories.UserRepository;
import com.backend.magicarpe.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import java.io.FileReader;
import java.time.Instant;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils utils;

    @Autowired
    private UserCheck userCheck;

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        UserResponse listUsers = new UserResponse(userRepository.findAll());
        return ResponseEntity.ok(listUsers.getUsers());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<?> getUser(@RequestHeader(value = "Authorization") String authorization) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");

        return ResponseEntity.ok(new UserResponse(userModified).getUser());
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/about.json")
    public ResponseEntity<?> about(@RequestHeader(value = "Authorization") String authorization, HttpServletRequest req) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/about.json"));

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject client = new JSONObject();
            JSONObject server = new JSONObject();

            long timestamp = Instant.now().getEpochSecond();


            client.put("host", req.getRemoteAddr());
            server.put("current_time", timestamp);

            JSONObject serverExist = (JSONObject) jsonObject.get("server");

            JSONArray services = (JSONArray) serverExist.get("services");

            server.put("services", services);

            jsonObject.put("client", client);
            jsonObject.put("server", server);

            return ResponseEntity.ok(jsonObject);

        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/me")
    public ResponseEntity<?> updateUser(@RequestHeader(value = "Authorization") String authorization, @RequestBody User user) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");

        if(user.getEmail() != null) userModified.setEmail(user.getEmail());
        if(user.getUsername() != null) userModified.setUsername(user.getUsername());
        userRepository.save(userModified);
        return ResponseEntity.ok(new MessageResponse("User modified."));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(@RequestHeader(value = "Authorization") String authorization) {
        String token = utils.getToken(authorization);
        userRepository.deleteById(utils.getIdFromJwtToken(token));
        return ResponseEntity.ok(new MessageResponse("Your account has been deleted."));
    }
}
