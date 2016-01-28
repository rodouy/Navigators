package org.navigators.financial.model;

import android.util.Log;

import java.util.Date;

/**
 * Created by rodolfo on 1/20/16.
 */
public class User {


    private final String LOG_TAG = User.class.getSimpleName();

    //region Fields
    private String username;
    private String password;
    private boolean status;
    private String statusmessage;
    private String group;
    private String entity;
    private Date lastlogin;
    private Date lastpwdchg;
    private boolean admin;
    private String[] adminapps;

    // endregion;

    //region Properties

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Date getLastpwdchg() {
        return lastpwdchg;
    }

    public void setLastpwdchg(Date lastpwdchg) {
        this.lastpwdchg = lastpwdchg;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String[] getAdminapps() {
        return adminapps;
    }

    public void setAdminapps(String[] adminapps) {
        this.adminapps = adminapps;
    }

    //endregion;

    //region Constructors

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = false;
        this.statusmessage = "";
        this.admin = false;
    }

    public User(String username, String password,
                boolean status, String statusmessage,
                String group, String entity, Date lastlogin,
                Date lastpwdchg, boolean admin,
                String[] adminapps) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.statusmessage = statusmessage;
        this.group = group;
        this.entity = entity;
        this.lastlogin = lastlogin;
        this.lastpwdchg = lastpwdchg;
        this.admin = admin;
        this.adminapps = adminapps;

        Log.v(LOG_TAG, "Create User: "
                + "username-" + this.username + ", "
                + "status-" + this.status + ", "
                + "statusmessage-" + this.statusmessage + ", "
                + "group-" + this.group + ", "
                + "entity-" + this.entity + ", "
                + "lastlogin-" + this.lastlogin + " ");
    }

    //endregion;

    //region Methods

    //endregion;

    //region Overrides


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null);

    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", status=" + status +
                ", group='" + group + '\'' +
                ", entity='" + entity + '\'' +
                ", lastlogin=" + lastlogin +
                '}';
    }

    //endregion;




}
