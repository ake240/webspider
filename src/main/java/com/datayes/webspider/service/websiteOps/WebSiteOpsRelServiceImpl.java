package com.datayes.webspider.service.websiteOps;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.dao.websiteOps.IWebSiteOpsRelDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;

@Service("webSiteOpsRelService")
public class WebSiteOpsRelServiceImpl implements IWebSiteOpsRelService {

	@Resource
	private IWebSiteOpsRelDao webSiteOpsRelDao;
	
	@Override
	public List<WebSiteOpsRel> findWebSiteOpsList(int websiteOpsId) {
		List<WebSiteOpsRel> list = new LinkedList<WebSiteOpsRel>();
		try {
			list = webSiteOpsRelDao.enquiryParentList(websiteOpsId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<WebSiteOpsRel> enquiryParentList(int websiteOpsId) {
		List<WebSiteOpsRel> list = new LinkedList<WebSiteOpsRel>();
		try {
			list = webSiteOpsRelDao.enquiryParentList(websiteOpsId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<WebSiteOpsRel> enquiryChildrenList(int websiteOpsId) {
		List<WebSiteOpsRel> list = new LinkedList<WebSiteOpsRel>();
		try {
			list = webSiteOpsRelDao.enquiryChildrenList(websiteOpsId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(List<WebSiteOpsRel> list) {
		try {
			if(list == null || list.size() == 0){
				
			} else {
				webSiteOpsRelDao.addList(list);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean removeRel(int webSiteOpsId) {
		try {
			webSiteOpsRelDao.removeRecords(webSiteOpsId);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
