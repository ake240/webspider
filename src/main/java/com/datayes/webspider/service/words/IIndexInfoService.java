package com.datayes.webspider.service.words;

import java.util.Date;

import com.datayes.webspider.common.PageDTO;

public interface IIndexInfoService {
	
	public PageDTO enquiryIndexInfo(Long wordId, String indexType,Date beginDate, Date endDate, int pageNow, int pageSize);
	public PageDTO enquiryIndexInfo(String keyword, String indexType,Date beginDate, Date endDate, int pageNow, int pageSize);

}
