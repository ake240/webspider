package com.datayes.webspider.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	protected Class<?> entityClass;
	
	public BaseDao(){
		Class<?> c = this.getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
		    this.entityClass = (Class<?>) ((ParameterizedType) t).getActualTypeArguments()[0];
		}
	}
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public T findById(String queryString, Serializable id) {
		List<T> list = (List<T>)getHibernateTemplate().findByNamedQuery(queryString, id);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public T findByName(String queryString, String name) {
		List<T> list = (List<T>)getHibernateTemplate().findByNamedQuery(queryString, name);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<T> findAll(String queryString) {
		getHibernateTemplate().setCacheQueries(true);		
		return (List<T>)getHibernateTemplate().findByNamedQuery(queryString);
	}

	@Override
	public List<T> loadAll() {
		getHibernateTemplate().setCacheQueries(true);		
		return (List<T>) getHibernateTemplate().loadAll(entityClass);
	}

	@Override
	public Serializable findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = getHibernateTemplate().findByCriteria(criteria);
		if (!list.isEmpty()) {
			return ((Number) list.get(0)).intValue();
		}
		
		return 0;
	}
	
	@Override
	public List<T> findPage(DetachedCriteria criteria, int pageNow, int pageSize) {
		return (List<T>)getHibernateTemplate().findByCriteria(criteria, (pageNow - 1) * pageSize, pageSize);
	}
}
