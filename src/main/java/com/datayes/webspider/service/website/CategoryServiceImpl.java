package com.datayes.webspider.service.website;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.website.ICategoryDao;
import com.datayes.webspider.domain.website.Category;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService{

	@Resource
	private ICategoryDao categoryDao;
	
	@Override
	public Category getCategoryById(Integer id) {
		return categoryDao.get(id);
	}

	@Override
	@Transactional
	public void removeCategory(Category category) {
		categoryDao.delete(category);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Category category) {
		categoryDao.saveOrUpdate(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.findAll("getAllCategory");
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryDao.getCategoryByName(categoryName);
	}

	@Override
	public PageDTO enquiryCategoryPage(String categoryName, String status, int pageNow, int pageSize) {
		int total = categoryDao.enquiryCategoryCount(categoryName, status);
		List list = categoryDao.enquiryCategoryPage(categoryName, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}
	
}
