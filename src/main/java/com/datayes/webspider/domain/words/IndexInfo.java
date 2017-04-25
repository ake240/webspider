package com.datayes.webspider.domain.words;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruihua.huang on 2014/9/1.
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `indexid` int(11) NOT NULL,
 * `indexdate` date NOT NULL,
 * `indextype` varchar(50) NOT NULL,
 * `indexnum` int(11) NOT NULL,
 * `inserttime` datetime NOT NULL,
 * `updatetime` datetime NOT NULL,
 */
@Entity
@Table(name = "index_info")
public class IndexInfo {
    private long id;
    private long indexId;
    private Date indexDate;
    private String indexType;
    private long indexNum;
    private Date insertTime;
    private Date updateTime;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "indexid")
    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    @Column(name = "indexdate")
    public Date getIndexDate() {
        return indexDate;
    }

    public void setIndexDate(Date indexDate) {
        this.indexDate = indexDate;
    }

    @Column(name = "indextype")
    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    @Column(name = "indexnum")
    public long getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(long indexNum) {
        this.indexNum = indexNum;
    }

    @Column(name = "insertTime")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Column(name = "updateTime")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
