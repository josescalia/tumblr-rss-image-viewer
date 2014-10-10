package com.josescalia.tumblr.util.net;

/**
 * <p>Created by josescalia on 06/06/14.</p>
 *
 */
public class ProxyBase {
    private String proxyHost;
    private Integer proxyPort;
    private Boolean usingAuthentication;
    private String proxyUser;
    private  String proxyPass;

    public ProxyBase() {
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Boolean getUsingAuthentication() {
        return usingAuthentication;
    }

    public void setUsingAuthentication(Boolean usingAuthentication) {
        this.usingAuthentication = usingAuthentication;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public String getProxyPass() {
        return proxyPass;
    }

    public void setProxyPass(String proxyPass) {
        this.proxyPass = proxyPass;
    }

    @Override
    public String toString() {
        return "ProxyBase{" +
                "proxyHost='" + proxyHost + '\'' +
                ", proxyPort='" + proxyPort + '\'' +
                ", usingAuthentication=" + usingAuthentication +
                ", proxyUser='" + proxyUser + '\'' +
                ", proxyPass='" + proxyPass + '\'' +
                '}';
    }
}
