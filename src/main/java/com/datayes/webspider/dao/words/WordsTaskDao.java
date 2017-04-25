package com.datayes.webspider.dao.words;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.words.IndexTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository("keywordTaskDao")
public class WordsTaskDao extends BaseDao<IndexTask> implements IWordsTaskDao {


    @Override
    public List<IndexTask> findByExample(IndexTask task) {
        return getHibernateTemplate().findByExample(task);
    }

    @Override
    public List<IndexTask> findByParams(Map<String, String> params, int page, int pageSize) {
        DetachedCriteria dc = null;
        try {
            dc = getDetachedCriteria(params);
        } catch (ParseException e) {
            logger.error(e);
            return null;
        }
        return findPage(dc, page, pageSize);
    }

    private DetachedCriteria getDetachedCriteria(Map<String, String> params) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DetachedCriteria dc = DetachedCriteria.forClass(IndexTask.class);
        if (params != null) {

            if (params.get("word") != null && !"".equals(params.get("word")))
                dc.add(Restrictions.eq("indexId", Long.parseLong(params.get("word"))));
            if (params.get("status") != null && !"".equals(params.get("status")))
                dc.add(Restrictions.eq("fetchStatus", Integer.parseInt(params.get("status"))));
            if (params.get("type") != null && !"".equals(params.get("type")))
                dc.add(Restrictions.eq("fetchType", Integer.parseInt(params.get("type"))));
            if (params.get("endtime") != null && !"".equals(params.get("endtime"))) {
                dc.add(Restrictions.le("fetchEndTime", sdf.parse(params.get("endtime"))));
            }
            if (params.get("starttime") != null && !"".equals(params.get("starttime"))) {
                dc.add(Restrictions.ge("fetchStartTime", sdf.parse(params.get("starttime"))));
            }
        }
        return dc;
    }

    @Override
    public Integer taskCount(Map<String, String> params) {
        try {
            return (Integer) findCount(getDetachedCriteria(params));
        } catch (ParseException e) {
            logger.error(e);
        }
        return 0;
    }
}
