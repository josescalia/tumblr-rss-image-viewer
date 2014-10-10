package com.josescalia.tumblr.util.net;

import com.josescalia.tumblr.util.swing.LabelValue;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * <p>Created by josescalia on 18/06/14.</p>
 *<p>The class to read and write a property file</p>
 *
 */
public class ReadWritePropertyFile {
    static Logger logger = Logger.getLogger(ReadWritePropertyFile.class.getName());
    public static Properties prop = new Properties();
    public static int propReadMode = 0;
    public static int propWriteMode = 1;


    /**
     * Function to write proxy connection setting to property file
     * @param propFilePath the location of property file
     * @return true or false of action
     * */
    public static boolean writeProxyConnectionConfig(String propFilePath, ProxyConnection proxyConnection){
        try {
            OutputStream output = new FileOutputStream(propFilePath);
            byte[] encoded = Base64.encodeBase64(proxyConnection.getProxyPassword().getBytes());
            prop.put("use.proxy", String.valueOf(proxyConnection.getUseProxy()));
            prop.put("http.proxyHost", proxyConnection.getProxyHost());
            prop.put("http.proxyPort", proxyConnection.getProxyPort());
            prop.put("proxy.useAuth", String.valueOf(proxyConnection.getProxyUseAuth()));
            prop.put("proxy.username", proxyConnection.getProxyUsername());
            prop.put("proxy.password", new String(encoded));
            prop.store(output, null);
            return true;
        } catch (FileNotFoundException e) {
            logger.error("Property file not found " + e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error("Exception occured " + e.getMessage());
            return false;
        }
    }
    /**
     * This function to write or read property file for Proxy Connection
     * @param propFilePath the location of property file
     * */
    public static ProxyConnection readProxyConnectionConfig(String propFilePath) {
        ProxyConnection rProxyConnection = null;
            try {
                InputStream inputStream = new FileInputStream(propFilePath);
                prop.load(inputStream);
                rProxyConnection = new ProxyConnection();
                rProxyConnection.setUseProxy(Boolean.valueOf(prop.getProperty("use.proxy")));
                rProxyConnection.setProxyHost(prop.getProperty("http.proxyHost"));
                rProxyConnection.setProxyPort(prop.getProperty("http.proxyPort"));
                rProxyConnection.setProxyUseAuth(Boolean.valueOf(prop.getProperty("proxy.useAuth")));
                rProxyConnection.setProxyUsername(prop.getProperty("proxy.username"));
                byte[] decoded = Base64.decodeBase64(prop.getProperty("proxy.password"));
                rProxyConnection.setProxyPassword(new String(decoded));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return rProxyConnection;
    }

    /**
     * This function to write or read property file for Proxy Connection
     * @param propActMode 0 for read, 1 for write mode
     * @param propFilePath the location of property file
     * */
    public static ProxyBase proxyBasePropertyAction(int propActMode, String propFilePath, ProxyBase proxyBase) {
        ProxyBase rProxyBase = null;
        if (propActMode == propReadMode) {
            try {
                InputStream inputStream = new FileInputStream(propFilePath);
                prop.load(inputStream);
                rProxyBase = new ProxyBase();
                //rProxyConnection.setUseProxy(Boolean.valueOf(prop.getProperty("use.proxy")));
                rProxyBase.setProxyHost(prop.getProperty("http.proxyHost"));
                rProxyBase.setProxyPort(Integer.parseInt(prop.getProperty("http.proxyPort")));
                rProxyBase.setUsingAuthentication(Boolean.valueOf(prop.getProperty("proxy.useAuth")));
                rProxyBase.setProxyUser(prop.getProperty("proxy.username"));
                byte[] decoded = Base64.decodeBase64(prop.getProperty("proxy.password"));
                rProxyBase.setProxyPass(new String(decoded));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (propActMode == propWriteMode) { //towrite
            try {
                OutputStream output = new FileOutputStream(propFilePath);
                byte[] encoded = Base64.encodeBase64(proxyBase.getProxyPass().getBytes());
                //prop.put("use.proxy", String.valueOf(proxyBase.getUseProxy()));
                prop.put("http.proxyHost", proxyBase.getProxyHost());
                prop.put("http.proxyPort", proxyBase.getProxyPort());
                prop.put("proxy.useAuth", String.valueOf(proxyBase.getUsingAuthentication()));
                prop.put("proxy.username", proxyBase.getProxyUser());
                prop.put("proxy.password", new String(encoded));
                prop.store(output, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return rProxyBase;
        }
        return rProxyBase;
    }


    public static Boolean checkUseProxy(String propertyFilePath) {
        Boolean bResult = false;
        try {
            InputStream inputStream = new FileInputStream(propertyFilePath);
            prop.load(inputStream);
            bResult = Boolean.valueOf(prop.getProperty("use.proxy"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bResult;
    }


    /**
     * Function to read a property based on single property name
     * @param propFilePath the location of property file
     * @param propertyName the propertyName to read
     * @return a value of read property
     * */
    public static String readAppProperty(String propFilePath,String propertyName){
        try{
            InputStream inputStream = new FileInputStream(propFilePath);
            prop.load(inputStream);
            return prop.getProperty(propertyName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *Function to write property to a property file
     * @param propFilePath the location of property file
     * @return true or false
     */
    public static boolean writeAppProperty(String propFilePath,LabelValue property){
        try{
            OutputStream output = new FileOutputStream(propFilePath);
            prop.setProperty(property.getLabel(), (String) property.getValue());
            prop.store(output,null);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
