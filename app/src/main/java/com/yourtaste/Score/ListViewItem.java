package com.yourtaste.Score;


import android.graphics.drawable.Drawable;

public class ListViewItem {

    private float ratingStar;
    private String name;
    private String country;
    private Drawable iconDrawable ;



    public ListViewItem(Drawable icon, int ratingStar, String name, String country) {
        this.ratingStar = ratingStar;
        this.name = name;
        this.country = country;
        this.iconDrawable = icon;

    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }


    public float getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(float ratingStar) {
        this.ratingStar = ratingStar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

