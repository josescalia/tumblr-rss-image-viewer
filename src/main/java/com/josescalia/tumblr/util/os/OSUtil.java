/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josescalia.tumblr.util.os;

/**
 *
 * @author Josescalia
 */
public class OSUtil {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String osName(){
        System.out.println("The Operating System is " + OS);
        if (isWindows()) {
            //System.out.println("This is Windows");
            return "windows";
        } else if (isMac()) {
            //System.out.println("This is Mac");
            return "macintosh";
        } else if (isUnix()) {
            //System.out.println("This is Unix or Linux");
            return "unix";
        } else if (isSolaris()) {
            //System.out.println("This is Solaris");
            return "solaris";
        } else {
            //System.out.println("Your OS is not support!!");
            return "unidentified";
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }
    
    public static String getUserHome(){
        return System.getProperty("user.home");
    }
    public static void main(String[] args) {
        System.out.println("OS : " + System.getProperty("os.name").toLowerCase());
        System.out.println("drive : " + System.getProperty("user.home"));
    }
    
}
