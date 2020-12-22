package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Weather extends Widget {
    private String city;

    public Weather() {
        super(EWidget.WEATHER);
        this.isActivated = true;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
