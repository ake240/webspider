package com.datayes.webspider.domain.newsextractor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news_extractor_feedback")
public class NewsExtractorFeedback {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "news_url")
	private String newsUrl;
	
	@Column(name = "title_error")
	private Integer titleError;
	
	@Column(name = "publish_time_error")
	private Integer publishTimeError;
	
	@Column(name = "content_error")
	private Integer contentError;
	
	@Column(name = "has_error")
	private Integer hasError;
	
	@Column(name = "reportor")
	private String reportor;
	
	@Column(name = "news_extractor_version")
	private String newsExtractorVersion;
	
	@Column(name = "insert_time")
	private Date insertTime = new Date();
	
	@Column(name = "update_time")
	private Date updateTime;
	
	public NewsExtractorFeedback(){}
	
	public NewsExtractorFeedback(String newsUrl, Integer titleError, Integer publishTimeError, 
			Integer contentError, Integer hasError, String reportor, String newsExtractorVersion){
		this.newsUrl = newsUrl;
		this.titleError = titleError;
		this.publishTimeError = publishTimeError;
		this.contentError = contentError;
		this.hasError = hasError;
		this.reportor = reportor;
		this.newsExtractorVersion = newsExtractorVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public Integer getTitleError() {
		return titleError;
	}

	public void setTitleError(Integer titleError) {
		this.titleError = titleError;
	}

	public Integer getPublishTimeError() {
		return publishTimeError;
	}

	public void setPublishTimeError(Integer publishTimeError) {
		this.publishTimeError = publishTimeError;
	}

	public Integer getContentError() {
		return contentError;
	}

	public void setContentError(Integer contentError) {
		this.contentError = contentError;
	}

	public Integer getHasError() {
		return hasError;
	}

	public void setHasError(Integer hasError) {
		this.hasError = hasError;
	}

	public String getReportor() {
		return reportor;
	}

	public void setReportor(String reportor) {
		this.reportor = reportor;
	}

	public String getNewsExtractorVersion() {
		return newsExtractorVersion;
	}

	public void setNewsExtractorVersion(String newsExtractorVersion) {
		this.newsExtractorVersion = newsExtractorVersion;
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
