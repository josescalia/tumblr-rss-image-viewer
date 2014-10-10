/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josescalia.tumblr.app;

import com.josescalia.tumblr.app.core.ApplicationSplash;
import com.josescalia.tumblr.util.ApplicationConstants;
import com.josescalia.tumblr.util.net.ProxyConnection;
import com.josescalia.tumblr.util.net.ReadWritePropertyFile;
import com.josescalia.tumblr.util.swing.LabelValue;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *
 * @author josescalia
 */
public class Bootstrap {
    static Logger logger = Logger.getLogger(Bootstrap.class.getName());

    public ApplicationContext appContext;
    /*other global attribute to use in application*/
    public static final String applicationLog = "tumblr_rss_image_viewer.log";
    public static final String applicationErrorLog = "tumblr_rss_image_viewer_err.log";

    public static void main(String[] args) {
        final Bootstrap app = new Bootstrap();

        //initialize splashScreen
        final ApplicationSplash splash = new ApplicationSplash();
        splash.setTitle("Tumblr Rss Image Viewer");
        splash.setSplashImage(app.getSplashImage());
        splash.setIconImage(new ImageIcon(splash.getClass().getResource("/icons/tumblr.png")).getImage());
        splash.setVisible(true);
        //read or write applicationConfig

        new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() throws Exception {
                app.runApp(splash);
                return "Done";
            }

            protected void done() {
                System.gc();
                logger.info("Application Running..");
            }
        }.execute();
    }

    public void runApp(ApplicationSplash splash) {
        //application config business process
        createAndWriteAppConfig(splash);
        splash.updateStatus("Reading property file");
        updateSplashProgress(splash, 50, 80, 30);

        appContext = new ClassPathXmlApplicationContext(ApplicationConstants.APP_CONTEXT);
        MainFrame frame = new MainFrame();
        frame.setContext(appContext);
        frame.setAppLookAndFeel(ReadWritePropertyFile.readAppProperty(ApplicationConstants.APP_PROPERTY_FILE,"application.look-and-feel"));
        frame.setTitle((String) appContext.getBean("appTitle") + " Ver-"+(String) appContext.getBean("appVersion") );
        frame.setLblFooter("Created By : " + (String) appContext.getBean("appAuthor") +"@2014");

        splash.updateStatus("Loading Main Form");
        updateSplashProgress(splash, 80, 100, 30);
        splash.dispose();

        frame.doShow();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    /**
     * function to create and write application config
     */
    private static void createAndWriteAppConfig(ApplicationSplash splash) {
        //check property file
        File file = new File(ApplicationConstants.APP_PROPERTY_FILE);
        if (!file.exists()) {
            splash.updateStatus("Writing property file");
            updateSplashProgress(splash, 10, 50, 30);
            logger.info("Property file is not exist, begin writing...");

            //write property here
            ProxyConnection proxyConnection = new ProxyConnection();
            proxyConnection.setUseProxy(false);
            proxyConnection.setProxyHost("localhost");
            proxyConnection.setProxyPort("3128");
            proxyConnection.setProxyUseAuth(false);
            proxyConnection.setProxyUsername("username");
            proxyConnection.setProxyPassword("password");
            ReadWritePropertyFile.writeProxyConnectionConfig(ApplicationConstants.APP_PROPERTY_FILE, proxyConnection);

            //other configuration
            ReadWritePropertyFile.writeAppProperty(ApplicationConstants.APP_PROPERTY_FILE, new LabelValue(System.getProperty("user.home"), "default.download.dir"));
            ReadWritePropertyFile.writeAppProperty(ApplicationConstants.APP_PROPERTY_FILE, new LabelValue("", "default.folder.viewer"));
            ReadWritePropertyFile.writeAppProperty(ApplicationConstants.APP_PROPERTY_FILE, new LabelValue("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel", "application.look-and-feel"));//default look and feel

            //database save configuration
            ReadWritePropertyFile.writeAppProperty(ApplicationConstants.APP_PROPERTY_FILE, new LabelValue("Save Succeed", "msg.save.succeed"));
            ReadWritePropertyFile.writeAppProperty(ApplicationConstants.APP_PROPERTY_FILE, new LabelValue("Save Failed", "msg.save.failed"));
        }else{
            splash.updateStatus("Preparing Property File");
            updateSplashProgress(splash, 10, 50, 30);
        }
    }

    public ImageIcon getSplashImage() {
        //return new ImageIcon(getClass().getResource("/images/InternetCrawler-SplashImg.png"));
        return new ImageIcon(getClass().getResource(ApplicationConstants.SPLASH_SCREEN_IMAGE));
    }

    public static void updateSplashProgress(ApplicationSplash splash, int start, int end, int interval) {
        for (int i = start; i < end; i++) {
            try {
                splash.updateProgress(i);
                Thread.sleep(interval);
            } catch (Exception e) {
            }
        }
    }
}
