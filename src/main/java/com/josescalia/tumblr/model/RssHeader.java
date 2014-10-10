package com.josescalia.tumblr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/28/12
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssHeader implements Serializable {

    private static final long serialVersionUID = 882651489477750212L;
    private String headerTitle;
    private String headerLink;
    private String headerDesc;
    private String headerLanguage;
    private String headerAtomLink;
    private RssHeaderImage rssHeaderImage;
    private List<RssItem> itemList = new ArrayList<RssItem>();

    public RssHeader() {
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getHeaderLink() {
        return headerLink;
    }

    public void setHeaderLink(String headerLink) {
        this.headerLink = headerLink;
    }

    public String getHeaderDesc() {
        return headerDesc;
    }

    public void setHeaderDesc(String headerDesc) {
        this.headerDesc = headerDesc;
    }

    public String getHeaderLanguage() {
        return headerLanguage;
    }

    public void setHeaderLanguage(String headerLanguage) {
        this.headerLanguage = headerLanguage;
    }

    public String getHeaderAtomLink() {
        return headerAtomLink;
    }

    public void setHeaderAtomLink(String headerAtomLink) {
        this.headerAtomLink = headerAtomLink;
    }

    public RssHeaderImage getRssHeaderImage() {
        return rssHeaderImage;
    }

    public void setRssHeaderImage(RssHeaderImage rssHeaderImage) {
        this.rssHeaderImage = rssHeaderImage;
    }

    public List<RssItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<RssItem> itemList) {
        this.itemList = itemList;
    }

    public void addItem(RssItem rssItem){
        this.itemList.add(rssItem);
    }

    @Override
    public String toString() {
        return "RssHeader{" +
                "headerTitle='" + headerTitle + '\'' +
                ", headerLink='" + headerLink + '\'' +
                ", headerDesc='" + headerDesc + '\'' +
                ", headerLanguage='" + headerLanguage + '\'' +
                ", headerAtomLink='" + headerAtomLink + '\'' +
                ", rssHeaderImage=" + rssHeaderImage +
                ", itemList=" + itemList +
                '}';
    }
}
