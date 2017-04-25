package com.datayes.webspider.dao.website;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.website.Category;

public interface ICategoryDao extends IBaseDao<Category> {
	public Category getCategoryByName(String categoryName);
	
	public int enquiryCategoryCount(String categoryName, String status);
	
	public List<Category> enquiryCategoryPage(String categoryName, String status, int pageNow, int pageSize);
}
