package com.tutorial.repo.registration.web;

import java.io.Serializable;

public class ListUserReq implements Serializable {

    private static final long serialVersionUID = -6556664339671689121L;

    private String userName;

    public ListUserReq(String userName) {
        this.userName = userName;
    }

    public ListUserReq() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
