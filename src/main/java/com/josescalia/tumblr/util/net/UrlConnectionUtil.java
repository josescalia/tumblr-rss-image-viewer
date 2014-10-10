package com.josescalia.tumblr.util.net;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by josescalia on 19/06/14.
 */
public class UrlConnectionUtil {

    static Logger logger = Logger.getLogger(UrlConnectionUtil.class.getName());

    public static CloseableHttpResponse getHttpResponseFromUrl(ProxyConnection proxyConnection, String url) {
        //logger.info(proxyConnection);
        CloseableHttpResponse response = null;
        //begin downloading
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyConnection.getProxyHost(), Integer.parseInt(proxyConnection.getProxyPort())),
                new UsernamePasswordCredentials(proxyConnection.getProxyUsername(), proxyConnection.getProxyPassword()));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        try {
            HttpHost target = new HttpHost(constructUri(url).getHost());
            HttpGet httpget = new HttpGet(constructUri(url).getPath());

            if (proxyConnection.getUseProxy()) {
                HttpHost proxy = new HttpHost(proxyConnection.getProxyHost(), Integer.parseInt(proxyConnection.getProxyPort()));

                RequestConfig config = RequestConfig.custom()
                        .setProxy(proxy)
                        .build();
                httpget.setConfig(config);
                logger.info("Executing request " + httpget.getRequestLine() + " to " + target + " via " + proxy);

            }
            response = httpclient.execute(target, httpget);
        } catch (Exception e){
            e.printStackTrace();
        } /*finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return response;
    }

    private static URI constructUri(String url) {
        try {
            URIBuilder builder = new URIBuilder(url);
            return builder.build();

        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
