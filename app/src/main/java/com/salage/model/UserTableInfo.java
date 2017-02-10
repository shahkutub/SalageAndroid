package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class UserTableInfo {
    private String ID;
    private String USER_USERNAME;
    private String USER_PASSWORD;
    private String USER_DESCRIPTION;
    private String USER_ISADMIN;

    private String USER_STATUS;

    public UserTableInfo() {
    }

    public UserTableInfo(String USER_USERNAME, String USER_PASSWORD, String USER_DESCRIPTION, String USER_ISADMIN,
                         String USER_STATUS) {
        this.USER_USERNAME = USER_USERNAME;
        this.USER_PASSWORD = USER_PASSWORD;
        this.USER_DESCRIPTION = USER_DESCRIPTION;
        this.USER_ISADMIN = USER_ISADMIN;
        this.USER_STATUS = USER_STATUS;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUSER_USERNAME() {
        return USER_USERNAME;
    }

    public void setUSER_USERNAME(String USER_USERNAME) {
        this.USER_USERNAME = USER_USERNAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public String getUSER_DESCRIPTION() {
        return USER_DESCRIPTION;
    }

    public void setUSER_DESCRIPTION(String USER_DESCRIPTION) {
        this.USER_DESCRIPTION = USER_DESCRIPTION;
    }

    public String getUSER_ISADMIN() {
        return USER_ISADMIN;
    }

    public void setUSER_ISADMIN(String USER_ISADMIN) {
        this.USER_ISADMIN = USER_ISADMIN;
    }

    public String getUSER_STATUS() {
        return USER_STATUS;
    }

    public void setUSER_STATUS(String USER_STATUS) {
        this.USER_STATUS = USER_STATUS;
    }
}
