package com.datayes.webspider.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;


@ParentPackage("webspider")
@Namespace("/")
@Results({ @Result(name = "success", location = "/WEB-INF/jsp/index.jsp"), })
public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	
	@Action("index")
	public String index(){
		return SUCCESS;
	}
}
