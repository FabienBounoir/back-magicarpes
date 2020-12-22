package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Instagram extends Widget {

    private String account;

    public Instagram() {
        super(EWidget.INSTAGRAM);
        this.isActivated = true;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
