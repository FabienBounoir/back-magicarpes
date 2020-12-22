package com.backend.magicarpe.payloads.request;

import com.backend.magicarpe.model.Widget;
import java.util.ArrayList;
import java.util.List;


public class WidgetRequest {

    private String widget;

    private List<Widget> widgets = new ArrayList<>();

    private String id;

    public String getWidget() {
        return this.widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }

    public List<Widget> getWidgets() {
        return this.widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
