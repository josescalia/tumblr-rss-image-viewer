package com.josescalia.tumblr.dao.impl;

import com.josescalia.tumblr.dao.RssDao;
import com.josescalia.tumblr.model.Rss;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by josescalia on 08/06/14.
 */
@Repository
public class RssDaoImpl implements RssDao {

    @Autowired
    private SessionFactory sessionFactory;


    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    public int getCount(Map<String, Object> mapParam) throws Exception{
        int iResult;
        String sQuery = " from Rss ";

        if (mapParam != null) {
            String field = mapParam.get("searchCat").toString();
            Object value = mapParam.get("searchVal");
            sQuery += " WHERE ";
            sQuery += field + " like " + "'%" + value + "%'";
        }
        System.out.println(sQuery);
        try {
            iResult = getCurrentSession().createQuery(sQuery).list().size();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return iResult;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rss> getPaginatedList(Map<String, Object> mapParams, int startRow, int lengthRow) {
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Rss> getFilteredList(Map<String, Object> mapParam) throws Exception{
        List<Rss> rList = new ArrayList<Rss>();
        String field = "";
        Object value = "";

        Criteria criteria = getCurrentSession().createCriteria(Rss.class);


        if (mapParam != null) {

            field = mapParam.get("searchCat").toString();
            value = mapParam.get("searchVal");
            if (value instanceof String) {
                criteria.add(Restrictions.ilike(field, String.valueOf(value), MatchMode.ANYWHERE));
            } else {
                criteria.add(Restrictions.like(field, value));
            }
        }

        try {
            rList = criteria.list();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return rList;
    }

    @Override
    @Transactional
    public Rss save(Rss entity) throws Exception{
        if(entity != null){
            getCurrentSession().saveOrUpdate(entity);
            return entity;
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id)throws Exception {
        Rss temp = (Rss) getCurrentSession().get(Rss.class,id);
        if(temp != null){
            try{
                getCurrentSession().delete(temp);
                return true;
            }catch (Exception e){
                throw new Exception();
            }
        }else {
            return false;
        }
    }
}
