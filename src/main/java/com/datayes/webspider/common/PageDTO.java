package com.datayes.webspider.common;

import java.util.List;

public class PageDTO {
	private List list;
	private int pageNow;
	private int pageCount;
	private int pageSize;
	private int total;
	private boolean isFirstPage;
	private boolean isLastPage;
	
	public PageDTO(){}
	
	public PageDTO(List list, int pageNow, int pageSize, int total){
		this.list = list;
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.total = total;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPageNow() {
		int pageCount = getPageCount();
		if (pageCount == 0) {
			return 0;
		}
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageCount() {
		return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean getIsFirstPage() {
		int pageCount = getPageCount();
		if (pageCount == 0) {
			return true;
		}
		return pageNow == 1;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean getIsLastPage() {
		int pageCount = getPageCount();
		if (pageCount == 0) {
			return true;
		}
		return pageNow == pageCount;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

}
