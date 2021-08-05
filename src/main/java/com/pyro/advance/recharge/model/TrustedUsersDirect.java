package com.pyro.advance.recharge.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "trusted_users_direct")
public class TrustedUsersDirect {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "allowed_ips")
    private String allowedIPS;
    @Column(name = "status")
    private String status;
    @Column(name = "allowed_services")
    private String allowedServices;
    @Column(name = "iccid")
    private String iccid;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAllowedIPS() {
        return allowedIPS;
    }

    public void setAllowedIPS(String allowedIPS) {
        this.allowedIPS = allowedIPS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAllowedServices() {
        return allowedServices;
    }

    public void setAllowedServices(String allowedServices) {
        this.allowedServices = allowedServices;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    @Override
    public String toString() {
        return "TrustedUsersDirect{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", allowedIPS='" + allowedIPS + '\'' +
                ", status='" + status + '\'' +
                ", allowedServices='" + allowedServices + '\'' +
                ", iccid='" + iccid + '\'' +
                '}';
    }
}
