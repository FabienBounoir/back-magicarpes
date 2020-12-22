package com.backend.magicarpe.controllers;

import com.backend.magicarpe.middleware.UserCheck;
import com.backend.magicarpe.model.*;
import com.backend.magicarpe.payloads.request.WidgetRequest;
import com.backend.magicarpe.payloads.response.MessageResponse;
import com.backend.magicarpe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/widgets")
public class WidgetController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserCheck userCheck;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getWidgets(@RequestHeader(value = "Authorization") String authorization) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");
        return ResponseEntity.ok(userModified.getWidgets());
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addWidget(@RequestHeader(value = "Authorization") String authorization, @RequestBody WidgetRequest widgetRequest) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");
        try {
            userModified.addWidget(widgetRequest.getWidget());
            userRepository.save(userModified);
            return ResponseEntity.ok(new MessageResponse("The widget has been added."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateWidget(@RequestHeader(value = "Authorization") String authorization, @RequestBody WidgetRequest widgetRequest) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");

        userModified.setWidgets(widgetRequest.getWidgets());
        userRepository.save(userModified);
        return ResponseEntity.ok(new MessageResponse("The widget has been modified."));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<?> deleteWidget(@RequestHeader(value = "Authorization") String authorization, @RequestBody WidgetRequest widgetRequest) {
        User userModified = userCheck.checkIdentity(authorization);
        if(userModified == null) return ResponseEntity.badRequest().body("Error.");
        try {
            userModified.deleteWidget(widgetRequest.getId());
            userRepository.save(userModified);
            return ResponseEntity.ok(new MessageResponse("The widget has been deleted."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
