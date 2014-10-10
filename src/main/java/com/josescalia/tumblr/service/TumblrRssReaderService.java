package com.josescalia.tumblr.service;

import com.josescalia.tumblr.app.Bootstrap;
import com.josescalia.tumblr.model.RssHeader;
import com.josescalia.tumblr.util.ApplicationConstants;
import com.josescalia.tumblr.util.RssReaderCore;
import com.josescalia.tumblr.util.net.ProxyBase;
import com.josescalia.tumblr.util.net.ProxyConnection;
import com.josescalia.tumblr.util.net.ReadWritePropertyFile;
import org.springframework.stereotype.Service;

/**
 * Created by josescalia on 27/07/14.
 */
@Service
public class TumblrRssReaderService {

    private static ProxyBase setProxyBase(){
        ProxyConnection proxyConnection = ReadWritePropertyFile.readProxyConnectionConfig(ApplicationConstants.APP_PROPERTY_FILE);
        ProxyBase proxyBase = new ProxyBase();
        proxyBase.setProxyHost(proxyConnection.getProxyHost());
        proxyBase.setProxyPort(Integer.parseInt(proxyConnection.getProxyPort()));
        proxyBase.setUsingAuthentication(proxyConnection.getProxyUseAuth());
        proxyBase.setProxyUser(proxyConnection.getProxyUsername());
        proxyBase.setProxyPass(proxyConnection.getProxyPassword());
        return proxyBase;
    }

    public RssHeader getRssHeader(String url) {
        RssReaderCore app = new RssReaderCore(url);
        app.setUseProxy(ReadWritePropertyFile.checkUseProxy(ApplicationConstants.APP_PROPERTY_FILE));
        app.setProxyBase(setProxyBase());
        return app.getRssItemList();
    }
}
