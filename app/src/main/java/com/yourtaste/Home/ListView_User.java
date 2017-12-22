package com.yourtaste.Home;

import android.graphics.drawable.Drawable;


public class ListView_User {
    private Drawable mUserIcon;
    private String mUserName;
    private String mUserPhoneNumber;

    ListView_User(Drawable userIcon, String userName, String userPhoneNumber) {
        mUserIcon = userIcon;
        mUserName = userName;
        mUserPhoneNumber = userPhoneNumber;
    }

    public Drawable getUserIcon(){
        return mUserIcon;
    }

    public String getUserName() {
        return mUserName;
    }

    public String  getUserPhoneNumber() {
        return mUserPhoneNumber;
    }

    public void  setUserPhoneNumber(String num) {
       mUserPhoneNumber=num;
    }
}