package com.mopital.doctor.models;

/**
 * Created by AlperCem on 22.3.2015.
 */
public class DrawerItem {
    private String title;
    private int imagePath;

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public DrawerItem(String title, int imagePath) {
        this.title = title;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
