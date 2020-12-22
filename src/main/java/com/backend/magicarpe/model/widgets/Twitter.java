package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Twitter extends Widget {
    private String account;

    public Twitter() {
        super(EWidget.TWITTER);
        this.isActivated = true;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
