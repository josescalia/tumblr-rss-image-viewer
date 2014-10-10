package com.josescalia.tumblr.util;


import com.josescalia.tumblr.app.Bootstrap;
import com.josescalia.tumblr.util.model.CacheFile;
import com.josescalia.tumblr.util.net.ReadWritePropertyFile;
import com.josescalia.tumblr.util.swing.UIAlert;
import org.apache.log4j.Logger;
import org.springframework.util.FileSystemUtils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by josescalia on 13/07/14.
 */
public class FileUtility {
    static Logger logger = Logger.getLogger(FileUtility.class.getName());

    private static final long K = 1024;
    private static final long M = K * K;
    private static final long G = M * K;
    private static final long T = G * K;

    public static CacheFile getCacheFile(File cacheFolder) {
        return new CacheFile(cacheFolder.getName(), totalFile(cacheFolder), convertToStringRepresentation(folderSize(cacheFolder)), Arrays.asList(getFilList(cacheFolder)));
    }

    private static File[] getFilList(File folder) {
        return folder.listFiles();
    }

    private static int totalFile(File folder) {
        if (folder.isDirectory()) {
            return folder.listFiles().length;
        }
        return 0;
    }

    private static long folderSize(File directory) {
        long length = 0;
        if (directory.isDirectory()) {
            List<File> fileList = Arrays.asList(directory.listFiles());
            if (fileList.size() > 0) {
                for (File file : directory.listFiles()) {
                    if (file.isFile())
                        length += file.length();
                    else
                        length += folderSize(file);
                }
            }
        }
        return length;
    }


    private static String convertToStringRepresentation(final long value) {
        if (value != 0) {
            final long[] dividers = new long[]{T, G, M, K, 1};
            final String[] units = new String[]{"TB", "GB", "MB", "KB", "B"};
            if (value < 1)
                throw new IllegalArgumentException("Invalid file size: " + value);
            String result = null;
            for (int i = 0; i < dividers.length; i++) {
                final long divider = dividers[i];
                if (value >= divider) {
                    result = format(value, divider, units[i]);
                    break;
                }
            }
            return result;
        }
        return "0 B";
    }

    private static String format(final long value,
                                 final long divider,
                                 final String unit) {
        final double result =
                divider > 1 ? (double) value / (double) divider : (double) value;
        return String.format("%.1f %s", Double.valueOf(result), unit);
    }

    private static long getTotalCountOfFolder(File folder) {
        long totalCount = 0;
        if (folder.isDirectory()) {
            List<File> fileList = Arrays.asList(folder.listFiles());
            if (fileList.size() > 0) {
                for (File file : fileList) {
                    totalCount += file.length();
                    //System.out.println(file.length());
                }
            }


        }
        return totalCount;
    }

    public static String readFile(String fileName) {
        //String sCurrLine = null;
        StringBuilder sReturn = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNextLine()) {
                sReturn.append(scanner.nextLine()).append(NL);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return sReturn.toString();
    }

    public static void openFolder(File folderName) {
        String folderViewer = ReadWritePropertyFile.readAppProperty(ApplicationConstants.APP_PROPERTY_FILE, "default.folder.viewer");
        if (!folderViewer.equals("")) {
            String[] commands = new String[]{folderViewer, folderName.getAbsolutePath()};
            try {
                Runtime.getRuntime().exec(commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Desktop desktop = Desktop.getDesktop();
            final String osName = System.getProperty("os.name");
            if (osName.startsWith("windows")) {
                try {
                    Runtime.getRuntime().exec(" explorer " + folderName.getAbsolutePath());
                } catch (IOException e) {
                    logger.error("Folder not found :" + e.getMessage());
                    UIAlert.showError(null, "Couldn't open folder : " + folderName.getAbsolutePath());
                }
            } else if (osName.startsWith("Linux")) {
                try {
                    desktop.open(folderName);
                } catch (IOException e) {
                    logger.error("Folder not found :" + e.getMessage());
                    UIAlert.showError(null, "Couldn't open folder : " + folderName.getAbsolutePath());
                }
            }
        }
    }


    public static void copyFile(File targetFile, File destinationFolder) throws IOException {
        if(targetFile.isFile()){
            FileSystemUtils.copyRecursively(targetFile,destinationFolder);
        }

    }
}
