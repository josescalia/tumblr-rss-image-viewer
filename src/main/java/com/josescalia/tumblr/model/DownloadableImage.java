package com.josescalia.tumblr.model;

/**
 * Created by josescalia on 08/06/14.
 */
public class DownloadableImage {
    private String url;
    private String urlType;
    private Boolean selected;


    public DownloadableImage() {
    }

    public DownloadableImage(String url, String urlType, Boolean selected) {
        this.url = url;
        this.urlType = urlType;
        this.selected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "DownloadableImage{" +
                "url='" + url + '\'' +
                ", urlType='" + urlType + '\'' +
                ", selected=" + selected +
                '}';
    }
}
