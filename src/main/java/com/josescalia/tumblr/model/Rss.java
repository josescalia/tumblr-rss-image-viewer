package com.josescalia.tumblr.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/28/12
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tumblr_rss")
public class Rss  {

    //private static final long serialVersionUID = 3009922543095220188L;
    private Long id;
    private String title;
    private String link;

    public Rss() {
    }

    /*public Rss(String link) {
        this.link = link;
    }*/


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 20)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "rss_title", length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "rss_link", length = 100, unique = true)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Rss{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
