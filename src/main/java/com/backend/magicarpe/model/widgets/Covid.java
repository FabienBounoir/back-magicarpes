package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Covid extends Widget {
    private String country;

    public Covid() {
        super(EWidget.COVID);
        this.isActivated = true;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
