/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josescalia.tumblr.util.os;

import javax.swing.JOptionPane;

/**
 * A Class contains functions to call <b>Operating System</b> procedure
 * @author Josescalia
 */
public class OperatingSystemUtil {

    private static String osName = System.getProperty("os.name");

    /**
     * Function to open integrated internet browser on operating system
     * @param sUrl URL Address to open*/
    public static void openInternetBrowser(String sUrl) {
        try {
            if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler " + sUrl);
            } else {
                String[] browsers = {"firefox", "opera", "konqueror",
                    "epiphany", "mozilla", "netscape"};
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[]{"which", browsers[count]})
                            .waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                Runtime.getRuntime().exec(new String[]{browser, sUrl});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in opening browser"
                    + ":\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Function to open a programs/application on the operating system<br>
     * The programs will automatically open based on file type
     * @param document document to open using registered program
     * */
    public static void openParameterizedProgram(String document) {
        try {
            if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler " + document);
            } else {
                String[] appArray = {"gedit", "kedit"};
                String application = null;
                for (int count = 0; count < appArray.length && application == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[]{"which", appArray[count]})
                            .waitFor() == 0) {
                        application = appArray[count];
                    }
                }
                Runtime.getRuntime().exec(new String[]{application, document});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in opening browser"
                    + ":\n" + e.getLocalizedMessage());
        }
    }

    /**
     * Function to open a program based on parameter definition
     * @param app a program name
     * @param document a document to open
     * */
    public static void openParameterizedProgram(String app, String document) {
        try {
            Runtime.getRuntime();
            Runtime.getRuntime().exec(app + " " + document);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in " + app + " with param " + document
                    + ":\n" + e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        OperatingSystemUtil.openParameterizedProgram("E:\\test.xml");
    }
}
