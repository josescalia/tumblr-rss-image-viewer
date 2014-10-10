/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josescalia.tumblr.source;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Josescalia
 */
public class RssModel {

    private String title;
    private String description;
    private String link;
    private String guid;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "RssModel{" + "title=" + title + ", description=" + description + ", link=" + link + ", guid=" + guid + ", pubDate=" + pubDate + '}';
    }

}
