/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josescalia.tumblr.util;

import com.josescalia.tumblr.model.Rss;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author Josescalia
 */
public class XmlUtility {

    public static void main(String[] args) {
        XmlUtility xmlUtility = new XmlUtility();

    }


    public Document createRssXMLTemp(List<Rss> listRss) {
        Document doc = getDocumentBuilder().newDocument();
        Element top = doc.createElement("db");
        doc.appendChild(top);
        Element root = doc.getDocumentElement();
        Element list = null;
        for (Rss rss : listRss) {
            list = doc.createElement("rss");

            Element elId = doc.createElement("id");
            Text txtElId = doc.createTextNode(String.valueOf(rss.getId()));
            elId.appendChild(txtElId);
            list.appendChild(elId);

            Element elTitle = doc.createElement("title");
            Text txtElTitle = doc.createTextNode(String.valueOf(rss.getTitle()));
            elTitle.appendChild(txtElTitle);
            list.appendChild(elTitle);

            Element elLink = doc.createElement("link");
            Text txtElLink = doc.createTextNode(String.valueOf(rss.getLink()));
            elLink.appendChild(txtElLink);
            list.appendChild(elLink);

            root.appendChild(list);
        }
        return doc;
    }

    private DocumentBuilder getDocumentBuilder() {
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        if ((db == null) || (dbf == null)) {
            // Step 1: create a DocumentBuilderFactory and configure it
            dbf = DocumentBuilderFactory.newInstance();
            // Optional: set various configuration options
            dbf.setValidating(false);
            // Step 2: create a DocumentBuilder that satisfies the constraints
            // specified by the DocumentBuilderFactory
            try {
                db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException pce) {
                System.err.println(pce);
            }
        }
        return db;
    }

    public String serializeDom(Document document) {
        DomSerializer serializer = new DomSerializer();
        return serializer.serialize(document);
    }
}
