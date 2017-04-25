package com.datayes.webspider.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.datayes.webspider.domain.websiteOps.DataStoreField;
import com.datayes.webspider.service.crawlrule.ICrawlruleService;
import com.datayes.webspider.service.websiteOps.IDataStoreFieldService;
import com.kepler.spider.integration.bo.CrawlRuleBO;
import com.kepler.spider.integration.bo.CrawlRuleNodeBO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MyTest {

	@Autowired
	private IDataStoreFieldService dataStoreFieldService;
	
	@Autowired
	private ICrawlruleService crawlruleService;
	
	public int getFieldId(String cnName,int id){
		List<DataStoreField> list = dataStoreFieldService.getByFieldCnName(cnName);
		if(list != null){
			if(list.size() == 1){
				return list.get(0).getDataStoreFieldId();
			}
			if(list.size() > 1 && id != 0){
				for(int i = 0;i < list.size() ; i++){
					int dataStoreTypeId = list.get(i).getDataStoreType().getDataStoreTypeId();
					if(id == dataStoreTypeId){
						return list.get(i).getDataStoreFieldId();
					}
				}				
			}
		}
		return 0;
	}
	
	@Test
	public void test() {
		int id  = getFieldId("卖家ID", 9);
		System.out.println(id);
	}
	
	@Test
	public void test2(){
		List<CrawlRuleBO> list = crawlruleService.getList(CrawlRuleBO.class);
		CrawlRuleBO crawlRuleBO = list.get(0);
	}
	/*
	@Test
	public void test3(){
		List<CrawlRuleBO> list = crawlruleService.getList(CrawlRuleBO.class);
		Integer dataStoreType;
		int count = 0;
		for(int i = 0;i < list.size();i++){
			//crawlRuleBO 文档
			CrawlRuleBO crawlRuleBO = list.get(i);
			
			int listLen = crawlRuleBO.getCrawlRuleNodeList().size();
			dataStoreType = crawlRuleBO.getDataStoreTypeId();
			
			//开始处理文档
			for(int j = 0;j < listLen;j++){
				//crawlRuleNode：文档下的一级list
				CrawlRuleNodeBO crawlRuleNode = crawlRuleBO.getCrawlRuleNodeList().get(j);
				int type = crawlRuleNode.getNodeType();
				if(type == 2){
					//crawlRuleNode为列表类型不需要处理,寻找其childlist
					
					List<CrawlRuleNodeBO> child = crawlRuleNode.getChildCrawlRuleNodeList();
					
					for(int k = 0;k < child.size();k++){
						
						CrawlRuleNodeBO childNode = child.get(k);
						
						//获取child的id
						int childId = getFieldId(childNode.getCrawlNodeCnName(),dataStoreType == null?0:dataStoreType);
						if(childId != 0){
							childNode.setCrawlNodeId(childId);
						}
					}
				}
				else{
					//crawlRuleNode为节点类型直接处理他本身
					//id
					int id = getFieldId(crawlRuleNode.getCrawlNodeCnName(),dataStoreType == null?0:dataStoreType);
					if(id != 0){
						crawlRuleNode.setCrawlNodeId(id);
					}
				}
			}
			crawlruleService.save(crawlRuleBO);	
			System.out.println(count++ + " / " + list.size());
		}


	}*/
	
	
	/**
	 * 单个失败文档
	 */
	/*@Test
	public void test4(){
		List<CrawlRuleBO> list = crawlruleService.getList(CrawlRuleBO.class);
		Integer dataStoreType;
		//crawlRuleBO 文档
		CrawlRuleBO crawlRuleBO = list.get(61);
		
		int listLen = crawlRuleBO.getCrawlRuleNodeList().size();
		dataStoreType = crawlRuleBO.getDataStoreTypeId();
		
		//开始处理文档
		for(int j = 0;j < listLen;j++){
			//crawlRuleNode：文档下的一级list
			CrawlRuleNodeBO crawlRuleNode = crawlRuleBO.getCrawlRuleNodeList().get(j);
			int type = crawlRuleNode.getNodeType();
			if(type == 2){
				//crawlRuleNode为列表类型不需要处理,寻找其childlist
				
				List<CrawlRuleNodeBO> child = crawlRuleNode.getChildCrawlRuleNodeList();
				
				for(int k = 0;k < child.size();k++){
					
					CrawlRuleNodeBO childNode = child.get(k);
					
					//获取child的id
					int childId = getFieldId(childNode.getCrawlNodeCnName(),dataStoreType == null?0:dataStoreType);
					if(childId != 0){
						childNode.setCrawlNodeId(childId);
					}
				}
			}
			else{
				//crawlRuleNode为节点类型直接处理他本身
				//id
				int id = getFieldId(crawlRuleNode.getCrawlNodeCnName(),dataStoreType == null?0:dataStoreType);
				if(id != 0){
					crawlRuleNode.setCrawlNodeId(id);
				}
			}
		}
		crawlruleService.save(crawlRuleBO);	
	}*/

}
