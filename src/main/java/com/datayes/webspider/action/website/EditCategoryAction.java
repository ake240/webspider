package com.datayes.webspider.action.website;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.website.Category;
import com.datayes.webspider.service.website.ICategoryService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", type = "redirectAction", params = {"actionName", "enquiryCategory"} ),
	@Result(name = "json", type = "json", params = {"includeProperties", "jsonResult"})
})
public class EditCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private Integer categoryId;
	private String categoryName;
	private Integer parentCategoryId;
	
	private String jsonResult;
	
	@Resource
	private ICategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(EditCategoryAction.class);

	@Action("newCategory")
	public String newCategory(){
		boolean succeed = true;
		String msg = "";
		
		if (categoryName != null) {
			Category category = new Category();
			Category existedCategory = categoryService.getCategoryByName(categoryName);
			
			if (existedCategory != null) {
				succeed = false;
				msg = "分类名称已经存在";
			}
			
			if (succeed) {
				if (parentCategoryId != null) {
					Category parentCategory = categoryService.getCategoryById(parentCategoryId);
					if (parentCategory != null) {
						category.setParentCategory(parentCategory);
					} else {
						succeed = false;
						msg = "父分类" + parentCategoryId + "不存在";
					}
				}
			}
			
			if (succeed) {
				category.setCategoryName(categoryName);
				category.setUpdateTime(new Date());
				categoryService.saveOrUpdate(category);
				logger.info("new category succeed - categoryName={},parentCategoryId={}", categoryName, parentCategoryId);
			}
		}else{
			succeed = false;
			msg = "分类名称不允许为空";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}
	
	@Action("editCategory")
	public String editCategory(){
		boolean succeed = true;
		String msg = "";
		
		if (categoryId != null) {
			Category category = categoryService.getCategoryById(categoryId);
			if (category != null) {
				if (!category.getCategoryName().equals(categoryName)) {
					Category existedCategory = categoryService.getCategoryByName(categoryName);
					if (existedCategory != null) {
						succeed = false;
						msg = "分类名称已经存在";
					} else {
						category.setCategoryName(categoryName);
					}
				}
				
				if (succeed) {
					if (parentCategoryId != null) {
						Category parentCategory = categoryService.getCategoryById(parentCategoryId);
						if (parentCategory != null) {
							category.setParentCategory(parentCategory);
						} else {
							succeed = false;
							msg = "父分类不存在";
						}
					}else{
						category.setParentCategory(null);
					}
				}
				
				if (succeed) {
					category.setUpdateTime(new Date());
					categoryService.saveOrUpdate(category);
					logger.info("edit category succeed - categoryId={}", categoryId);
				}
			} else {
				succeed = false;
				msg = "找不到对应的category";
			}
		} else {
			succeed = false;
			msg = "categoryId为空，无法编辑";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}
	
	@Action("removeCategory")
	public String removeCategory(){
		boolean succeed = true;
		String msg = "";
		if (categoryId != null) {
			Category categoryInDB = categoryService.getCategoryById(categoryId);
			if (categoryInDB != null) {
				categoryService.removeCategory(categoryInDB);
				logger.info("remove category succeed - categoryId={}", categoryId);
			} else {
				succeed = false;
				msg = "没有找到分类ID为" + categoryId + "的分类";
			}
		} else {
			succeed = false;
			msg = "分类ID为空,无法进行删除";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
}
