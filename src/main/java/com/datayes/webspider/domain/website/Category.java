package com.datayes.webspider.domain.website;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@NamedQueries({
	@NamedQuery(name="getCategoryById", query="from Category c where c.categoryId = ?"),
	@NamedQuery(name="getCategoryByName", query="from Category c where c.categoryName = ?"),
	@NamedQuery(name="getAllCategory", query="from Category c order by c.insertTime desc"),
})
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "categoryid", nullable = false, unique = true)
	private Integer categoryId;

	@Column(name = "categoryname")
	private String categoryName;
	
	@OneToMany(mappedBy = "parentCategory")
	private Set<Category> childCategories;

	@ManyToOne
	@JoinColumn(name = "pcategoryid", referencedColumnName = "categoryid")
	private Category parentCategory;

	@Column(name = "inserttime")
	private Date insertTime = new Date();

	@Column(name = "updatetime")
	private Date updateTime;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="categories")
	private Set<WebSite> webSites;
	
	public Category() {
	}
	
	public Category(String categoryName){
		this.categoryName = categoryName;
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

	public Set<Category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
