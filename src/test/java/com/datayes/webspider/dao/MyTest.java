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
import com.kepler.util.validate.Validate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MyTest {

	@Autowired
	private IDataStoreFieldService dataStoreFieldService;
	
	@Autowired
	private ICrawlruleService crawlruleService;
	
	public int getFieldId(CrawlRuleNodeBO childNode,int dataStoreTypeId){
		String fieldCnName = childNode.getCrawlNodeCnName();
		String fieldEnName = childNode.getCrawlNodeEnName();
		DataStoreField dataStoreField = dataStoreFieldService.getDataStoreFieldByFieldCnName(fieldCnName, dataStoreTypeId);
		if(dataStoreField != null){
			return dataStoreField.getDataStoreFieldId();
		}
		DataStoreField dataStoreField2 = dataStoreFieldService.getDataStoreFieldByFieldEnName(fieldEnName, dataStoreTypeId);
		if(dataStoreField2 != null){
			return dataStoreField2.getDataStoreFieldId();
		}
		return 0;
	}
	
	@Test
	public void test2(){
		List<CrawlRuleBO> list = crawlruleService.getList(CrawlRuleBO.class);
		if(!Validate.isEmpty(list)){
			for(CrawlRuleBO crawlRuleBO : list){
				System.out.println(crawlRuleBO.getCrawlRuleName());
			}
		}
	}
	@Test
	public void test3(){
		List<CrawlRuleBO> list = crawlruleService.getList(CrawlRuleBO.class);
		Integer dataStoreType;
		int count = 0;
		for(int i = 0;i < list.size();i++){
			//crawlRuleBO 文档
			try{
				CrawlRuleBO crawlRuleBO = list.get(i);
//				String crawlRuleId = crawlRuleBO.getId();
				
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
//							Integer crawlNodeId = childNode.getCrawlNodeId();
//							if(crawlNodeId == null || crawlNodeId == 0){
//								System.out.println("crawlRuleId = " + crawlRuleBO.getId() + ",crawlRuleName = " + crawlRuleBO.getCrawlRuleName() + ", dataStoreType = "+dataStoreType+", ruleNodeEnName = "+childNode.getCrawlNodeEnName()+", ruleNodeName = " + childNode.getCrawlNodeCnName() + " is miss.");
//							}
							//获取child的id
							int childId = getFieldId(childNode,dataStoreType == null?0:dataStoreType);
							if(childId != 0){
								childNode.setCrawlNodeId(childId);
							}else{
								System.out.println("crawlRuleId = " + crawlRuleBO.getId() + ",crawlRuleName = " + crawlRuleBO.getCrawlRuleName() + ", dataStoreType = "+dataStoreType+", ruleNodeEnName = "+childNode.getCrawlNodeEnName()+", ruleNodeName = " + childNode.getCrawlNodeCnName() + " is miss.");
							}
						}
					}
					else{
						//crawlRuleNode为节点类型直接处理他本身
						//id
//						Integer crawlNodeId = crawlRuleNode.getCrawlNodeId();
//						if(crawlNodeId == null || crawlNodeId == 0){
//							System.out.println("crawlRuleId = " + crawlRuleBO.getId() + ",crawlRuleName = " + crawlRuleBO.getCrawlRuleName() + ", dataStoreType = "+dataStoreType+", ruleNodeEnName = "+crawlRuleNode.getCrawlNodeEnName()+", ruleNodeName = " + crawlRuleNode.getCrawlNodeCnName() + " is miss.");
//						}
						int id = getFieldId(crawlRuleNode,dataStoreType == null?0:dataStoreType);
						if(id != 0){
							crawlRuleNode.setCrawlNodeId(id);
						}else{
							System.out.println("crawlRuleId = " + crawlRuleBO.getId() + ",crawlRuleName = " + crawlRuleBO.getCrawlRuleName() + ", dataStoreType = "+dataStoreType+", ruleNodeEnName = "+crawlRuleNode.getCrawlNodeEnName()+", ruleNodeName = " + crawlRuleNode.getCrawlNodeCnName() + " is miss.");
						}
					}
				}
				crawlruleService.save(crawlRuleBO);	
				//System.out.println(count++ + " / " + list.size());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("finish");
	}
	
	
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
