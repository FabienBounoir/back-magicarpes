package com.backend.magicarpe.models;

import com.backend.magicarpe.exception.NoSuchWidgetException;
import com.backend.magicarpe.model.*;
import com.backend.magicarpe.model.widgets.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class allModelTest {


    @Test
    void createUser() {
        User user = new User("test", "test@test.com", "aqwzsxedc");

        assertEquals(user.getEmail(), "test@test.com");
        assertEquals(user.getUsername(), "test");
        assertEquals(user.getPassword(), "aqwzsxedc");

        user.setId(new ObjectId().toString());

        assertFalse(user.getId().isEmpty());
    }

    @Test
    void addRoleToUser() {

        User user = new User("test", "test@test.com", "aqwzsxedc");

        Set<Role> roles = new HashSet<>();

        Role role = new Role();
        role.setName(ERole.ROLE_USER);

        assertEquals(role.getName(), ERole.ROLE_USER);

        roles.add(role);

        user.setRoles(roles);

        assertEquals(user.getRoles(), roles);
    }

    @Test
    void createWidget() {

        Widget youtube = new Youtube();
        Widget twitch = new Twitch();
        Widget twitter = new Twitter();
        Widget steam = new Steam();
        Widget weather = new Weather();
        Widget covid = new Covid();

        assertEquals(youtube.getName(), EWidget.YOUTUBE);
        assertEquals(twitch.getName(), EWidget.TWITCH);
        assertEquals(twitter.getName(), EWidget.TWITTER);
        assertEquals(steam.getName(), EWidget.STEAM);
        assertEquals(weather.getName(), EWidget.WEATHER);
        assertEquals(covid.getName(), EWidget.COVID);

        assertFalse(youtube.getId().isEmpty());
        assertFalse(twitch.getId().isEmpty());
        assertFalse(twitter.getId().isEmpty());
        assertFalse(steam.getId().isEmpty());
        assertFalse(weather.getId().isEmpty());
        assertFalse(covid.getId().isEmpty());
    }

    @Test
    void addWidgetToUser() throws NoSuchWidgetException {
        User user = new User("test", "test@test.com", "aqwzsxedc");

        user.addWidget("youtube");

        assertFalse(user.getWidgets().isEmpty());
        assertEquals(user.getWidgets().get(0).getName(), EWidget.YOUTUBE);

        assertThrows(NoSuchWidgetException.class, () -> user.addWidget("La Rafal"));
    }

    @Test
    void deleteWidgetToUser() throws NoSuchWidgetException {
        User user = new User("test", "test@test.com", "aqwzsxedc");

        user.addWidget("youtube");

        String widgetId = user.getWidgets().get(0).getId();

        user.deleteWidget(widgetId);

        assertTrue(user.getWidgets().isEmpty());
    }
}
