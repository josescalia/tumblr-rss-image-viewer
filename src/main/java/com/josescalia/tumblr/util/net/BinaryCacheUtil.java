package com.josescalia.tumblr.util.net;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by josescalia on 18/06/14.
 */
public class BinaryCacheUtil {

    static Logger logger = Logger.getLogger(BinaryCacheUtil.class.getName());
    private static String osName = System.getProperty("os.name");

    public static String getBinaryImagePath(String storagePath, String fileName, String downloadUrl) {
        logger.info("Storage Path : " + storagePath);
        if (downloadUrl == null || downloadUrl.equals("")) {
            if (osName.startsWith("Windows"))
                return new File(storagePath + "\\ImageNotFound-180x225.png").getAbsolutePath();
            else
                return new File(storagePath + "/ImageNotFound-180x225.png").getAbsolutePath();
        }

        String sReturn = "";
        File folderPath = new File(storagePath);
        //System.out.println(folderPath.getAbsolutePath());
        if (folderPath.isDirectory()) {
            File targetFile = null;
            if (osName.startsWith("Windows"))
                targetFile = new File(storagePath + "\\" + fileName);
            else
                targetFile = new File(storagePath + "/" + fileName);

            //check target file is exist
            if (targetFile.exists()) {
                logger.info("File " + fileName + " in folder " + targetFile.getAbsolutePath() + " found...!!");
                return targetFile.getAbsolutePath();
            } else {
                logger.info("File " + fileName + " in folder " + targetFile.getAbsolutePath() + " not found, begin downloading...!!");

                downloadingBinary(folderPath, downloadUrl, fileName);
                return targetFile.getAbsolutePath();
            }
        }

        return sReturn;
    }


    private static Boolean applicationUseProxy() {
        Boolean bResult = false;

        return false;
    }

    private static void downloadingBinary(File storagePath, String downloadUrl, String filename) {
        logger.info("Downloading Binary..!!!");
        logger.info("url to download : " + downloadUrl);
        logger.info("Folder to store : " + storagePath.getAbsolutePath());
        CloseableHttpResponse response = null;

        try {
            //declare file to download
            File file = null;
            if (osName.startsWith("windows")) {
                file = new File(storagePath + "\\" + filename);
            } else {
                file = new File(storagePath + "/" + filename);
            }

            //declare file outputstream
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            ProxyConnection proxyConnection = ReadWritePropertyFile.readProxyConnectionConfig("config.properties");
            //response = httpclient.execute(target, httpget);
            response = UrlConnectionUtil.getHttpResponseFromUrl(proxyConnection,downloadUrl);

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
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
