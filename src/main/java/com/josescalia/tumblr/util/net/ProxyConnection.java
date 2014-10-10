package com.josescalia.tumblr.util.net;

/*
 * Created by josescalia on 18/06/14.
 */

/**
 * This class is define a proxy connection
 * to use as methodology to setConnection bridge
 *
 * */
public class ProxyConnection {
    private Boolean useProxy;
    private String proxyHost;
    private String proxyPort;
    private Boolean proxyUseAuth;
    private String proxyUsername;
    private String proxyPassword;


    public Boolean getUseProxy() {
        return useProxy;
    }

    public void setUseProxy(Boolean useProxy) {
        this.useProxy = useProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Boolean getProxyUseAuth() {
        return proxyUseAuth;
    }

    public void setProxyUseAuth(Boolean proxyUseAuth) {
        this.proxyUseAuth = proxyUseAuth;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return this.proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    @Override
    public String toString() {
        return "ProxyConnection{" +
                "useProxy=" + useProxy +
                ", proxyHost='" + proxyHost + '\'' +
                ", proxyPort='" + proxyPort + '\'' +
                ", proxyUseAuth=" + proxyUseAuth +
                ", proxyUsername='" + proxyUsername + '\'' +
                ", proxyPassword='" + proxyPassword + '\'' +
                '}';
    }
}
