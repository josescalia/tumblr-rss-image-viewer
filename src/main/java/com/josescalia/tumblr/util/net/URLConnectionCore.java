package com.josescalia.tumblr.util.net;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by josescalia on 11/06/14.
 */
public class URLConnectionCore  {

    public static void main(String[] args) {
        URLConnectionCore.getConnection();
    }


    public static void getConnection(){
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("192.168.181.254", 8080),
                new UsernamePasswordCredentials("muhammady", "mono132"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        try {
            HttpHost target = new HttpHost("http://image.tmdb.org/t/p/w500/y3iZibhyEmFCrdRENGaSZHM2fSm.jpg");
            HttpHost proxy = new HttpHost("192.168.181.254", 8080);

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet httpget = new HttpGet("/");
            httpget.setConfig(config);

            System.out.println("Executing request " + httpget.getRequestLine() + " to " + target + " via " + proxy);
            File path = new File(".");
            File file = new File(path +"/y3iZibhyEmFCrdRENGaSZHM2fSm.jpg");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            CloseableHttpResponse response = httpclient.execute(target, httpget);
            try {
                try {
                    fileOutputStream.write(EntityUtils.toByteArray(response.getEntity()));
                    fileOutputStream.close();
                } finally {
                    response.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
