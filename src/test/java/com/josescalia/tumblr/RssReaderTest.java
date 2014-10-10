package com.josescalia.tumblr;

import com.josescalia.tumblr.app.Bootstrap;
import com.josescalia.tumblr.model.RssHeader;
import com.josescalia.tumblr.model.RssItem;
import com.josescalia.tumblr.util.ApplicationConstants;
import com.josescalia.tumblr.util.RssReaderCore;
import com.josescalia.tumblr.util.net.ProxyBase;
import com.josescalia.tumblr.util.net.ProxyConnection;
import com.josescalia.tumblr.util.net.ReadWritePropertyFile;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by josescalia on 11/06/14.
 */
public class  RssReaderTest {

    @Test
    public void testCoreConnection(){
        RssReaderCore app = new RssReaderCore("http://2ndvenus.tumblr.com/rss");
        app.setUseProxy(ReadWritePropertyFile.checkUseProxy(ApplicationConstants.APP_PROPERTY_FILE));
        app.setProxyBase(setProxy());
        //app.setUseProxy(false);
        /*app.setProxyHost("192.168.181.254");
        app.setProxyPort(8080);
        app.setUseProxyAuth(false);
        app.setProxyUsername("muhammady");
        app.setProxyPassword("mono132");
        */
        RssHeader header = app.getRssItemList();

        Assert.assertNotNull(header);

        System.out.println(header.getHeaderTitle());
        for(RssItem rssItem : header.getItemList() ){
            System.out.println(rssItem);
        }
    }

    private static ProxyBase setProxy(){
        ProxyConnection proxyConnection = ReadWritePropertyFile.readProxyConnectionConfig(ApplicationConstants.APP_PROPERTY_FILE);
        ProxyBase proxyBase = new ProxyBase();
        proxyBase.setProxyHost(proxyConnection.getProxyHost());
        proxyBase.setProxyPort(Integer.parseInt(proxyConnection.getProxyPort()));
        proxyBase.setUsingAuthentication(proxyConnection.getProxyUseAuth());
        proxyBase.setProxyUser(proxyConnection.getProxyUsername());
        proxyBase.setProxyPass(proxyConnection.getProxyPassword());
        return proxyBase;
    }
}
