package com.datayes.webspider.action.websiteOps;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsParamService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editWebSiteOpsParam.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditWebSiteOpsParamAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Integer fieldId;
	private Integer webSiteOpsId;
	private WebSiteOperationParam webSiteOpsParam;
	private List<WebSiteOperationParam> webSiteOpsParams;
	private int type;
	private String jsonResult;
	private String message;
	private String allParams;
	
	@Resource
	private IWebSiteOpsParamService webSiteOpsParamService;
	
	@Resource 
	private IWebSiteOpsService webSiteOpsService;

	@Action("webSiteOpsParam")
	public String webSiteOpsParam(){
		if (type==1) {
			webSiteOpsParams = webSiteOpsParamService.getByWebSiteOpsId(webSiteOpsId);
			this.allParams = compositeParams();
		}
		return "page";
	}
	
	private String compositeParams(){
		StringBuilder sb = new StringBuilder();
		for (WebSiteOperationParam opsParam : webSiteOpsParams) {
			int filedFlag = opsParam.getFieldFlag();
			int placeHolderFlag = opsParam.getPlaceholderFlag();
			String fieldEnName = opsParam.getFieldEnName();
			String placeholder = opsParam.getPlaceholder();
			String filedValue = opsParam.getFieldValue();
			
			if (filedFlag == 0) {
				if (placeHolderFlag == 0) {
					sb.append(filedValue);
				}else{
					String str = StringUtils.isEmpty(placeholder) ? fieldEnName : placeholder;
					sb.append("@").append(str).append("@");
				}
			}else if(filedFlag == 1){
				if(placeHolderFlag == 0){
	                  if(sb.toString().endsWith("?")){
	                     sb.append(fieldEnName).append("=").append(filedValue);
	                  }else{
	                     sb.append("&").append(fieldEnName).append("=").append(filedValue);
	                  }
	              }else{
	            	  String str = StringUtils.isEmpty(placeholder) ? fieldEnName : placeholder;
	                  if(sb.toString().endsWith("?")){
	                     sb.append(fieldEnName).append("=").append("@").append(str).append("@");
	                  }else{
	                      sb.append("&").append(fieldEnName).append("=").append("@").append(str).append("@");
	                  }
	              }
			}
		}
		return sb.toString();
	}
	
	@Action("newWebSiteOpsParam")
	public String newWebSiteOpsParam(){
		boolean succeed = true;
		
		WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
		if (webSiteOps != null) {
			if (webSiteOpsParams != null) {
				for (WebSiteOperationParam webSiteOpsParam: webSiteOpsParams) {
					if (StringUtils.isEmpty(webSiteOpsParam.getFieldValue())) {
						webSiteOpsParam.setFieldValue("");
					}
					if (StringUtils.isEmpty(webSiteOpsParam.getPlaceholder())) {
						webSiteOpsParam.setPlaceholder("");
					}
					if (StringUtils.isEmpty(webSiteOpsParam.getFieldDesc())){
						webSiteOpsParam.setFieldDesc("");
					}
					webSiteOpsParam.setWebSiteOps(webSiteOps);
					webSiteOpsParam.setOperators(1);
					webSiteOpsParam.setInsertTime(new Date());
					webSiteOpsParam.setUpdateTime(new Date());
					webSiteOpsParamService.saveOrUpdate(webSiteOpsParam);
				}
			}else{
				succeed = false;
				message = "网站操作类型参数必填字段不允许为空";
			}
		}else{
			succeed = false;
			message = "网站操作类型不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}else{
			return "page";
		}
	}
	
	@Action("editWebSiteOpsParam")
	public String editWebSiteOpsParam(){
		boolean succeed = true;
		WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
		if (webSiteOps != null) {
			if (webSiteOpsParams != null) {
				for (WebSiteOperationParam webSiteOpsParam: webSiteOpsParams) {
					if (webSiteOpsParam.getFieldId() != null) {
						WebSiteOperationParam webSiteOpsParamInDB = webSiteOpsParamService.getByFieldId(webSiteOpsParam.getFieldId());
						webSiteOpsParamInDB.setWebSiteOps(webSiteOps);
						webSiteOpsParamInDB.setFieldCnName(webSiteOpsParam.getFieldCnName());
						webSiteOpsParamInDB.setFieldEnName(webSiteOpsParam.getFieldEnName());
						webSiteOpsParamInDB.setFieldType(webSiteOpsParam.getFieldType());
						webSiteOpsParamInDB.setNeedEncode(webSiteOpsParam.getNeedEncode());
						webSiteOpsParamInDB.setNeedStore(webSiteOpsParam.getNeedStore());
						if (StringUtils.isEmpty(webSiteOpsParam.getFieldValue())) {
							webSiteOpsParamInDB.setFieldValue("");
						}else{
							webSiteOpsParamInDB.setFieldValue(webSiteOpsParam.getFieldValue());
						}
						webSiteOpsParamInDB.setFieldFlag(webSiteOpsParam.getFieldFlag());
						webSiteOpsParamInDB.setPlaceholderFlag(webSiteOpsParam.getPlaceholderFlag());
						webSiteOpsParamInDB.setSortNo(webSiteOpsParam.getSortNo());
						if (StringUtils.isEmpty(webSiteOpsParam.getPlaceholder())) {
							webSiteOpsParamInDB.setPlaceholder("");
						}else{
							webSiteOpsParamInDB.setPlaceholder(webSiteOpsParam.getPlaceholder());
						}
						if (StringUtils.isEmpty(webSiteOpsParam.getFieldDesc())){
							webSiteOpsParamInDB.setFieldDesc("");
						}else{
							webSiteOpsParamInDB.setFieldDesc(webSiteOpsParam.getFieldDesc());
						}
						
						webSiteOpsParamInDB.setUpdateTime(new Date());
						webSiteOpsParamService.saveOrUpdate(webSiteOpsParamInDB);
					}else{
						if (StringUtils.isEmpty(webSiteOpsParam.getFieldValue())) {
							webSiteOpsParam.setFieldValue("");
						}
						if (StringUtils.isEmpty(webSiteOpsParam.getPlaceholder())) {
							webSiteOpsParam.setPlaceholder("");
						}
						if (StringUtils.isEmpty(webSiteOpsParam.getFieldDesc())){
							webSiteOpsParam.setFieldDesc("");
						}
						webSiteOpsParam.setWebSiteOps(webSiteOps);
						webSiteOpsParam.setOperators(1);
						webSiteOpsParam.setInsertTime(new Date());
						webSiteOpsParam.setUpdateTime(new Date());
						webSiteOpsParamService.saveOrUpdate(webSiteOpsParam);
					}
				}
			}else {
				succeed = false;
				message = "网站操作类型参数必填字段不允许为空";
			}
		}else{
			succeed = false;
			message = "网站操作类型不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}else{
			return "page";
		}
	}
	
	@Action("deleteWebSiteOpsParam")
	public String deleteWebSiteOpsParam(){
		boolean succeed = true;
		String msg = "";
		WebSiteOperationParam webSiteOpsParamInDB = webSiteOpsParamService.getByFieldId(fieldId);
		if (webSiteOpsParamInDB != null) {
			webSiteOpsParamService.delete(webSiteOpsParamInDB);
		}else{
			succeed = false;
			msg = "找不到对应的操作类型参数, fieldId:" + fieldId;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public WebSiteOperationParam getWebSiteOpsParam() {
		return webSiteOpsParam;
	}

	public void setWebSiteOpsParam(WebSiteOperationParam webSiteOpsParam) {
		this.webSiteOpsParam = webSiteOpsParam;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public List<WebSiteOperationParam> getWebSiteOpsParams() {
		return webSiteOpsParams;
	}

	public void setWebSiteOpsParams(List<WebSiteOperationParam> webSiteOpsParams) {
		this.webSiteOpsParams = webSiteOpsParams;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAllParams() {
		return allParams;
	}

	public void setAllParams(String allParams) {
		this.allParams = allParams;
	}
	
}
