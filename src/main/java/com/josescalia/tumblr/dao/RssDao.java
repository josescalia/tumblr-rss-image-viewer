package com.josescalia.tumblr.dao;

import com.josescalia.tumblr.model.Rss;

import java.util.List;
import java.util.Map;

/**
 * Created by josescalia on 08/06/14.
 */
public interface RssDao {

    int getCount(Map<String, Object> mapParam) throws Exception;

    List<Rss> getPaginatedList(Map<String, Object> mapParams, int startRow, int lengthRow);

    List<Rss> getFilteredList(Map<String, Object> mapParams)  throws Exception;

    Rss save(Rss entity) throws Exception;

    Boolean delete(Long id) throws Exception;
}
