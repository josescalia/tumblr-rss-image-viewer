package com.josescalia.tumblr;

import com.josescalia.tumblr.model.Rss;
import com.josescalia.tumblr.service.RssService;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.context.ManagedSessionContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by josescalia on 08/06/14.
 */
@ContextConfiguration(locations = {"classpath:tumblrRssApplicationContext-test.xml"})
public class RssServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    private RssService rssService;

    @Before
    public void init() {
        session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
    }


    @Test
    public void testService(){
        Rss rss = new Rss();
        rss.setTitle("Masuk Busway");
        rss.setLink("http://masukbusway.com");

        //Assert.assertTrue(rssService.save(rss));

        rss = new Rss();
        rss.setLink("http://josescalia.tumblr.com");
        rss.setTitle("Josescalia");
        //Assert.assertTrue(rssService.save(rss));


        Assert.assertNotNull(rssService.getFilteredList(null));

    }

    @After
    public void destroySession(){
        session.disconnect();
    }

}
