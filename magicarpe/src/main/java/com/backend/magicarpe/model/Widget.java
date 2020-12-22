package com.backend.magicarpe.model;

import com.backend.magicarpe.model.widgets.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Youtube.class, name = "YOUTUBE"),
        @JsonSubTypes.Type(value = Twitch.class, name = "TWITCH"),
        @JsonSubTypes.Type(value = Weather.class, name = "WEATHER"),
        @JsonSubTypes.Type(value = Covid.class, name = "COVID"),
        @JsonSubTypes.Type(value = Steam.class, name = "STEAM"),
        @JsonSubTypes.Type(value = Twitter.class, name = "TWITTER"),
        @JsonSubTypes.Type(value = Spotify.class, name = "SPOTIFY"),
        @JsonSubTypes.Type(value = Instagram.class, name = "INSTAGRAM")
})
public abstract class Widget {

    @Id
    private String id;

    protected Boolean isActivated = false;

    protected EWidget name;

    public Widget(EWidget name) {
        this.name = name;
        this.id = new ObjectId().toString();
    }

    public String getId() {
        return this.id;
    }

    public EWidget getName() {
        return this.name;
    }

}
