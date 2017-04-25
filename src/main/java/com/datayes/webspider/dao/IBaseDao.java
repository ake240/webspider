package com.datayes.webspider.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDao<T> {
	public void saveOrUpdate(T t);
	
	public T findById(String queryString, Serializable id);
	
	public T findByName(String queryString, String name);
	
	public void delete(T t);
	
	public T load(Serializable id);
	
	public List<T> loadAll();
	
	public T get(Serializable id);
	
	public List<T> findAll(String queryString);
	
	public Serializable findCount(DetachedCriteria criteria);
	
	public List<T> findPage(DetachedCriteria criteria, int pageNow, int pageSize);
}
