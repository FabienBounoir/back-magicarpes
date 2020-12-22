package com.backend.magicarpe.controllers;

import com.backend.magicarpe.model.User;
import com.backend.magicarpe.payloads.request.SignupRequest;
import com.backend.magicarpe.payloads.response.MessageResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.geo.format.DistanceFormatter;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PrePostAdviceReactiveMethodInterceptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    private static final String signupUrl = "http://localhost:8080/api/auth/signup";
    private static final String signinUrl = "http://localhost:8080/api/auth/signin";
    private static final JSONParser parser = new JSONParser();

    @Test
     void registerUser() throws ParseException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        JSONObject userJson = new JSONObject();
        userJson.put("email", "guillaume.cirillo@gmail.com");
        userJson.put("username", "test1");
        userJson.put("password", "aqwzsxedc");


        HttpEntity<String> request =
                new HttpEntity<>(userJson.toString(), headers);

        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(signupUrl, HttpMethod.POST, request, String.class);

            Object obj = parser.parse(response.getBody());
            JSONObject jsonObject = (JSONObject) obj;

            assertEquals(jsonObject.get("message"), "User registered successfully!");

        } catch (Exception e) {

            Object obj = parser.parse(e.getMessage().split("[\\[\\]]")[1]);
            JSONObject jsonObject = (JSONObject) obj;

            assertEquals(jsonObject.get("message"), "Error: Username is already taken!");
        }
    }

    @Test
    void authenticateUser() throws ParseException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        JSONObject userJson = new JSONObject();
        userJson.put("username", "test1a");
        userJson.put("password", "aqwzsxedc");


        HttpEntity<String> request =
                new HttpEntity<>(userJson.toString(), headers);

        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(signinUrl, HttpMethod.POST, request, String.class);

            Object obj = parser.parse(response.getBody());
            JSONObject jsonObject = (JSONObject) obj;

            assertEquals(jsonObject.get("username"), "test1");
            assertEquals(jsonObject.get("email"), "guillaume.cirillo@gmail.com");
            assertTrue(jsonObject.containsKey("tokenType"));
            assertTrue(jsonObject.containsKey("id"));
            assertTrue(jsonObject.containsKey("accessToken"));
            assertEquals(jsonObject.get("roles").toString(), "[\"ROLE_USER\"]");

        } catch (Exception e) {

            Object obj = parser.parse(e.getMessage().split(" ")[0]);
            assertEquals(obj.toString(), "401");
        }
    }
}
