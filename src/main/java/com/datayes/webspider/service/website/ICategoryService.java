package com.datayes.webspider.service.website;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.website.Category;


public interface ICategoryService {
	public Category getCategoryById(Integer id);
	
	public void removeCategory(Category category);
	
	public Category getCategoryByName(String categoryName);
	
	public void saveOrUpdate(Category category);
	
	public List<Category> getAllCategory();
	
	public PageDTO enquiryCategoryPage(String categoryName, String status, int pageNow, int pageSize);
}
