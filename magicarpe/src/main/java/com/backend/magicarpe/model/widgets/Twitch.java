package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Twitch extends Widget {
    private String channel;

    public Twitch() {
        super(EWidget.TWITCH);
        this.isActivated = true;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
