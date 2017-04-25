package com.datayes.webspider.domain.words;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruihua.huang on 2014/9/1.
 * `taskid` int(11) NOT NULL AUTO_INCREMENT,
 * `indexId` int(11) NOT NULL,
 * `indexkeyword` varchar(255) NOT NULL,
 * `fetchstarttime` date DEFAULT NULL,
 * `fetchendtime` date DEFAULT NULL,
 * `fetchtype` tinyint(4) NOT NULL DEFAULT '0' COMMENT '抓取类型：1实时抓取，2：历史抓取，3：补抓',
 * `executeperiod` varchar(255) NOT NULL DEFAULT '' COMMENT '任务执行周期,以小时为单位。多个以逗号隔开。\r\n如：1,2,3,5-9\r\n\r\n',
 * `fetchstatus` tinyint(4) NOT NULL DEFAULT '0',
 * `fetchmsg` varchar(255) NOT NULL DEFAULT '',
 * `inserttime` datetime NOT NULL,
 * `updatetime` datetime NOT NULL,
 */
@Entity
@Table(name = "index_task")
public class IndexTask {

    @Id
    @GeneratedValue
    @Column(name = "taskid")
    private Long taskId;

    @Column(name = "indexId")
    private Long indexId;

    @Column(name = "indexkeyword")
    private String indexKeyword;

    @Column(name = "inserttime")
    private Date insertTime;

    @Column(name = "updatetime")
    private Date updateTime;

    @Column(name = "fetchType")
    private Integer fetchType;

    @Column(name = "fetchstatus")
    private int fetchStatus;
    @Column(name = "fetchstarttime")
    private Date fetchStartTime;
    @Column(name = "fetchendtime")
    private Date fetchEndTime;
    @Column(name = "fetchmsg")
    private String msg = "";
    /*
     * Getters and Setters
     */

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getFetchStartTime() {
        return fetchStartTime;
    }

    public void setFetchStartTime(Date fetchStartTime) {
        this.fetchStartTime = fetchStartTime;
    }

    public Date getFetchEndTime() {
        return fetchEndTime;
    }

    public void setFetchEndTime(Date fetchEndTime) {
        this.fetchEndTime = fetchEndTime;
    }

    public int getFetchStatus() {
        return fetchStatus;
    }

    public void setFetchStatus(int fetchStatus) {
        this.fetchStatus = fetchStatus;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getIndexKeyword() {
        return indexKeyword;
    }

    public void setIndexKeyword(String indexKeyword) {
        this.indexKeyword = indexKeyword;
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

    public Integer getFetchType() {
        return fetchType;
    }

    public void setFetchType(Integer fetchType) {
        this.fetchType = fetchType;
    }
}
