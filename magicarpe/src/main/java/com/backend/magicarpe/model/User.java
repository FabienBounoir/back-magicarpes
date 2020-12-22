package com.backend.magicarpe.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.backend.magicarpe.exception.NoSuchWidgetException;
import com.backend.magicarpe.model.widgets.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private List<Widget> widgets = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public void addWidget(String widget) throws NoSuchWidgetException {
        switch (widget) {
            case "youtube" -> this.widgets.add(new Youtube());
            case "twitch" -> this.widgets.add(new Twitch());
            case "weather" -> this.widgets.add(new Weather());
            case "covid" -> this.widgets.add(new Covid());
            case "steam" -> this.widgets.add(new Steam());
            case "twitter" -> this.widgets.add(new Twitter());
            case "spotify" -> this.widgets.add(new Spotify());
            case "instagram" -> this.widgets.add(new Instagram());
            default -> throw new NoSuchWidgetException("The widget doesn't exist.");
        }
    }

    public void deleteWidget(String widgetId) {
            this.getWidgets().removeIf(widget -> widget.getId().equals(widgetId));
    }

}