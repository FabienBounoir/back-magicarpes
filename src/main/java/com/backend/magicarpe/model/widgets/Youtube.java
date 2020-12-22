package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Youtube extends Widget {

    private String channel;

    public Youtube() {
        super(EWidget.YOUTUBE);
        this.isActivated = true;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
