package com.josescalia.tumblr.service;

import com.josescalia.tumblr.dao.RssDao;
import com.josescalia.tumblr.model.Rss;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by josescalia on 08/06/14.
 */
@Service
public class RssService {

    static Logger logger = Logger.getLogger(RssService.class.getName());

    @Autowired
    private RssDao rssDao;

    public int getCount(Map<String, Object> mapParam) {
        try {
            return rssDao.getCount(mapParam);
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    public List<Rss> getFilteredList(Map<String, Object> mapParams) {
        try {
            return rssDao.getFilteredList(mapParams);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Boolean save(Rss entity) throws Exception{
        try {
            return rssDao.save(entity) != null;
        } catch (Exception e) {
            logger.error(e);
            throw new Exception(e);
            //return false;
        }
    }

    public Boolean delete(Long id){
        try {
            return rssDao.delete(id);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

}
