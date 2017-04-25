package com.datayes.webspider.bo.words;

import java.util.Date;

import com.datayes.webspider.domain.words.IndexInfo;

public class IndexInfoBO {
    private long indexId;
    private String indexWord;
    private Date indexDate;
    private String indexType;
    private long indexNum;
    private Date insertTime;
    private Date updateTime;
    
    public IndexInfoBO(IndexInfo indexInfo){
    	setIndexId(indexInfo.getIndexId());
    	setIndexDate(indexInfo.getIndexDate());
    	setInsertTime(indexInfo.getInsertTime());
    	setUpdateTime(indexInfo.getUpdateTime());
    	setIndexType(indexInfo.getIndexType());
    	setIndexNum(indexInfo.getIndexNum());
    }
    
	public long getIndexId() {
		return indexId;
	}
	public void setIndexId(long indexId) {
		this.indexId = indexId;
	}
	public Date getIndexDate() {
		return indexDate;
	}
	public void setIndexDate(Date indexDate) {
		this.indexDate = indexDate;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public long getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(long indexNum) {
		this.indexNum = indexNum;
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
	public String getIndexWord() {
		return indexWord;
	}
	public void setIndexWord(String indexWord) {
		this.indexWord = indexWord;
	}
}
