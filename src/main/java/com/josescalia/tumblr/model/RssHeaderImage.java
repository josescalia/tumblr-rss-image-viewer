package com.josescalia.tumblr.model;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/29/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssHeaderImage {
    private String imageTitle;
    private String imageUrl;
    private String imageLink;

    public RssHeaderImage() {
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "RssHeaderImage{" +
                "imageTitle='" + imageTitle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
