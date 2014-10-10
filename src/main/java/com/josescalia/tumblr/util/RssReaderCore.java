package com.josescalia.tumblr.util;

import com.josescalia.tumblr.model.DownloadableImage;
import com.josescalia.tumblr.model.RssHeader;
import com.josescalia.tumblr.model.RssHeaderImage;
import com.josescalia.tumblr.model.RssItem;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.josescalia.tumblr.util.net.ProxyAuthenticator;
import com.josescalia.tumblr.util.net.ProxyBase;
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
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jdesktop.observablecollections.ObservableCollections;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/28/12
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * A Core class to serve as a core layer of the Rss Reader Application<br>
 * example to use:
 * <pre>
 RssReaderCore app = new RssReaderCore("http://2ndvenus.tumblr.com/rss");
 app.setUseProxy(false);
 app.setProxyHost(proxyHost);
 app.setProxyPort(proxyPort);
 app.setUseProxyAuth(false);
 app.setProxyUsername(proxyUsername);
 app.setProxyPassword(proxyPassword);
 RssHeader header = app.getRssItemList();
 * </pre>
 *
 */
public class RssReaderCore {
    private Logger logger = Logger.getLogger(RssReaderCore.class.getName());
    private RssHeader rssHeader;
    private File rssFile;
    private String rssUrl;

    //proxy
    private Boolean useProxy;
    private ProxyBase proxyBase;



    NodeList nodeList;
    Node node;
    Element e;

    public RssReaderCore() {
    }

    public RssReaderCore(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    /*getter and setter*/
    public RssHeader getRssHeader() {
        return rssHeader;
    }

    public void setRssHeader(RssHeader rssHeader) {
        this.rssHeader = rssHeader;
    }

    public File getRssFile() {
        return rssFile;
    }

    public void setRssFile(File rssFile) {
        this.rssFile = rssFile;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public void setRssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public ProxyBase getProxyBase() {
        return proxyBase;
    }

    public void setProxyBase(ProxyBase proxyBase) {
        this.proxyBase = proxyBase;
    }

    public Boolean getUseProxy() {
        return useProxy;
    }

    public void setUseProxy(Boolean useProxy) {
        this.useProxy = useProxy;
    }
    /**
     * Static function to extract nodes on xml content and casting it to RssHeader object and RssItem List
     *
     * @return RssHeader an object of RssHeader
     */
    public RssHeader getRssItemList() {
        printConnectionInfo();
        rssHeader = new RssHeader();
        getUrlContentWithHttpClient();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(rssFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
            nodeList = doc.getElementsByTagName("channel");
            if (nodeList.getLength() > 0) { //it is header
                node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    e = (Element) node;
                    rssHeader.setHeaderTitle(getTagValue("title", e));
                    rssHeader.setHeaderDesc(getTagValue("description", e));
                    rssHeader.setHeaderLink(getTagValue("link", e));
                    //rssHeader.setHeaderLanguage(getTagValue("language", e));
                }
            }
            //setImageRssHeader
            nodeList = doc.getElementsByTagName("image");
            if (nodeList.getLength() > 0) {
                node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    e = (Element) node;
                    RssHeaderImage image = new RssHeaderImage();
                    image.setImageTitle(getTagValue("title", e));
                    image.setImageLink(getTagValue("link", e));
                    image.setImageUrl(getTagValue("url", e));
                    rssHeader.setRssHeaderImage(image);
                }
            }

            //getRssItems
            nodeList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    e = (Element) node;
                    rssHeader.addItem(extractItem(e));
                }
            }

        } catch (Exception ex) {
            logger.info("Exception :" + ex.getMessage());
            //return null;
        }
        return rssHeader;
    }

    private RssItem extractItem(Element e) {
        String imgList = "";
        RssItem rItem = new RssItem();
        rItem.setTitle(getTagValue("title", e));
        rItem.setLink(getTagValue("link", e));
        rItem.setGuid(getTagValue("guid", e));
        rItem.setPubDate(getTagValue("pubDate", e));
        rItem.setCategory(getTagValue("category", e));
        for (String str : getImageTagFromString(getTagValue("description", e))) {
            //System.out.println(str);
            if (imgList.length() != 0) {
                imgList += "<br>";
                imgList += str;
            } else {
                imgList += str;
            }
        }
        rItem.setImage(imgList);
        //getDownloadableImageList(getImageTagFromString(getTagValue("description", e)));
        rItem.setImageList(ObservableCollections.observableList(getDownloadableImageList(getImageTagFromString(getTagValue("description", e)))));
        //removing all html comment and html tag
        rItem.setDescription(getTagValue("description", e).replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", ""));

        return rItem;
    }

    private List<DownloadableImage> getDownloadableImageList(List<String> imgList) {
        List<DownloadableImage> rList = null;
        if (imgList != null && imgList.size() > 0) {
            rList = new ArrayList<DownloadableImage>();
            for (String img : imgList) {
                rList.add(new DownloadableImage(img.replaceAll("<img src=\"", "").replaceAll("\"/>", ""), "", true));
                //System.out.println(img.replaceAll("<img src=\"", "").replaceAll("\"/>", ""));
            }
        }
        //System.out.println("done processing...");
        return rList;
    }

    public static List<String> getImageTagFromString(String description) {
        List<String> strList = new ArrayList<String>();
        String urlString = "<img\\b[^>]*src=\"[^>]*>(.*?)";
        Pattern pattern = Pattern.compile(urlString);
        Matcher urlMatcher = pattern.matcher(description);
        while (urlMatcher.find()) {
            int startIndex = urlMatcher.start();
            int endIndex = urlMatcher.end();
            String currentMatch = description.substring(startIndex, endIndex);
            strList.add(currentMatch);
        }
        //System.out.println("strList Length : " + strList.size());
        return strList;
    }

    /**
     * static function to get value of given xml element as params
     *
     * @param sTag     String tag name of nodes.
     * @param eElement Node of xml to get the value
     * @return String value of node
     */
    private static String getTagValue(String sTag, Element eElement) {
        try {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            if (nValue.getNodeValue() != null || !nValue.getNodeValue().isEmpty()) {
                return nValue.getNodeValue();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Static function to get XML Url Content, and then save it to temporary xml file
     * @deprecated : use <b>getUrlContentWithHttpClient</b>  method instead
     */
    private void getUrlContent() {
        URL url;
        try {
            url = new URL(rssUrl);
            URLConnection urlConnection = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;

            String fileName = "rss_temp.xml";
            rssFile = new File(fileName);

            if (!rssFile.exists()) {
                rssFile.createNewFile();
            }

            //use FileWriter to write file
            FileWriter fw = new FileWriter(rssFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
            }

            bw.close();
            br.close();

            logger.info("Creating xml cache done!!");

        } catch (MalformedURLException ex) {
            logger.info("MalFormed Url : " + ex);
        } catch (IOException ioe) {
            logger.info("IOException : " + ioe);
        }
    }

    private URI constructUri(String url){
        try {
            URIBuilder builder = new URIBuilder(url);
            //System.out.println(builder.getHost());
            //System.out.println(builder.getPort());
            return builder.build();

        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private void getUrlContentWithHttpClient(){
        CloseableHttpClient httpclient;

        if(proxyBase.getUsingAuthentication()) {
        //if(proxyBase.isu) {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(proxyBase.getProxyHost(), proxyBase.getProxyPort()),
                    new UsernamePasswordCredentials(proxyBase.getProxyUser(), proxyBase.getProxyPass()));
            httpclient = HttpClients.custom()
                    .setDefaultCredentialsProvider(credsProvider).build();
        }else{
            httpclient = HttpClients.createDefault();
        }

        try {

            HttpHost target = new HttpHost(constructUri(rssUrl).getHost());
            HttpHost proxy = new HttpHost(proxyBase.getProxyHost(), proxyBase.getProxyPort());
            HttpGet httpget = new HttpGet(constructUri(rssUrl).getPath());
            if(useProxy) {
                RequestConfig config = RequestConfig.custom()
                        .setProxy(proxy)
                        .build();
                httpget.setConfig(config);
                logger.info("Executing request " + httpget.getRequestLine() + " to " + target + " via " + proxy);
            }

            CloseableHttpResponse response = httpclient.execute(target, httpget);
            try {

                logger.info("Http Response status : " + response.getStatusLine());
                //EntityUtils.consume(response.getEntity());

                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String inputLine;
                String fileName = "rss_temp.xml";
                rssFile = new File(fileName);

                if (!rssFile.exists()) {
                    rssFile.createNewFile();
                }

                //use FileWriter to write file
                FileWriter fw = new FileWriter(rssFile.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                while ((inputLine = br.readLine()) != null) {
                    bw.write(inputLine);
                }

                bw.close();
                br.close();

                logger.info("Creating xml cache done!!");
            }catch (Exception e){
                logger.error("1." + e.getMessage());
            } finally{
                response.close();
            }
        } catch (Exception e){
            logger.error("2." +e);
        }finally {
            try {
                httpclient.close();
            }catch (Exception e){
                logger.error("3." +e.getMessage());
            }
        }
    }

    private void printConnectionInfo(){
        logger.info("========================= Connection info =========================");
        logger.info("Connection Using Proxy : " + useProxy);
        if(useProxy) {
            logger.info("Proxy Host : " + proxyBase.getProxyHost());
            logger.info("Proxy Port : " + proxyBase.getProxyPort());
            logger.info("Using Proxy Auth :" + proxyBase.getUsingAuthentication());
            if(proxyBase.getUsingAuthentication()){
                logger.info("Using Proxy Authentication with username : " + proxyBase.getProxyUser());
            }
        }

        logger.info("===================================================================");
    }

    public static void main(String[] args) throws ParseException {
                /*String strtoparse = "<img src=\"http://31.media.tumblr.com/bda5c8682ff4a95e52d1f7d7b5eec32c/tumblr_mrcrdtEv6m1sdbfjbo1_500.jpg\"/><br/><br/><p>Jalanan nggak terlalu rame waktu lebaran, karena sudah kebiasaan, tetep aja masuk jalur busway!</p>";
                for(String str: getImageTagFromString(strtoparse)){
                    System.out.println(str);
                }*/

//        String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";
//        Pattern pattern = Pattern.compile("\\w+");
//        // In case you would like to ignore case sensitivity you could use this
//        // statement
//        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(EXAMPLE_TEST);
//        // Check all occurance
//        while (matcher.find()) {
//            System.out.print("Start index: " + matcher.start());
//            System.out.print(" End index: " + matcher.end() + " ");
//            System.out.println(matcher.group());
//        }
//        // Now create a new pattern and matcher to replace whitespace with tabs
//        Pattern replace = Pattern.compile("\\s+");
//        Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
//        System.out.println(matcher2.replaceAll("\t"));
    }
}
