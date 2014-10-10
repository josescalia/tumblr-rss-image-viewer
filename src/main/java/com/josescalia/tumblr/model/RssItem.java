package com.josescalia.tumblr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/28/12
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssItem implements Serializable {

    private static final long serialVersionUID = -3234862089731580167L;
    private String title;
    private String link;
    private String guid;
    private String pubDate;
    private String category;
    private String description;
    private String image;
    private List<DownloadableImage> imageList;

    public RssItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<DownloadableImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<DownloadableImage> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "RssItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
