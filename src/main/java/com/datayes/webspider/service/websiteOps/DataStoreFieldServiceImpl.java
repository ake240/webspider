package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.websiteOps.IDataStoreFieldDao;
import com.datayes.webspider.domain.websiteOps.DataStoreField;

@Service("dataStoreFieldService")
public class DataStoreFieldServiceImpl implements IDataStoreFieldService {

	@Resource
	private IDataStoreFieldDao dataStoreFieldDao;
	
	@Override
	public Integer enquiryDataStoreFieldCount(Integer typeId) {
		return dataStoreFieldDao.enquiryDataStoreFieldCount(typeId);
	}

	@Override
	public PageDTO enquiryDataStoreFieldPage(Integer typeId, int pageNow, int pageSize) {
		int total = dataStoreFieldDao.enquiryDataStoreFieldCount(typeId);
		List list = dataStoreFieldDao.enquiryDataStoreFieldPage(typeId, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	@Transactional
	public void saveOrUpdate(DataStoreField dataStoreField) {
		dataStoreFieldDao.saveOrUpdate(dataStoreField);
	}

	@Override
	@Transactional
	public void deleteDataStoreField(DataStoreField dataStoreField) {
		dataStoreFieldDao.delete(dataStoreField);
	}

	@Override
	public DataStoreField findByDataStoreFieldId(Integer dataStoreFieldId) {
		return dataStoreFieldDao.findById("getByDataStoreFieldId", dataStoreFieldId);
	}

	@Override
	public List<DataStoreField> findByDataStoreTypeId(Integer dataStoreTypeId) {
		return dataStoreFieldDao.enquiryDataStoreFieldPage(dataStoreTypeId, 1, Integer.MAX_VALUE);
	}

	@Override
	public DataStoreField getDataStoreFieldByFieldCnName(String fieldCnName) {
		return dataStoreFieldDao.getDataStoreFieldByFieldCnName(fieldCnName);
	}

	@Override
	public List<DataStoreField> getByFieldCnName(String fieldCnName) {
		return dataStoreFieldDao.getByFieldCnName(fieldCnName);
	}

	@Override
	public String findByExtName(String extName) {
		return dataStoreFieldDao.getByExtName(extName);
	}
}
