package com.backend.magicarpe.model.widgets;

import com.backend.magicarpe.model.EWidget;
import com.backend.magicarpe.model.Widget;

public class Spotify extends Widget {

    private String nbPlaylist;

    public Spotify() {
        super(EWidget.SPOTIFY);
        this.isActivated = true;
    }

    public String getNbPlaylist() {
        return this.nbPlaylist;
    }

    public void setNbPlaylist(String nbPlaylist) {
        this.nbPlaylist = nbPlaylist;
    }

}
