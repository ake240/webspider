package com.datayes.webspider.domain.words;

import javax.persistence.*;
import java.sql.Date;

/*
  `indexid` int(11) NOT NULL AUTO_INCREMENT,
  `indexkeyword` varchar(255) NOT NULL,
  `isactive` tinyint(4) NOT NULL DEFAULT '0',
  `fetchstarttime` date DEFAULT NULL,
  `fetchendtime` date DEFAULT NULL,
  `fetchhistory` tinyint(4) NOT NULL DEFAULT '0',
  `fetchstatus` tinyint(4) NOT NULL DEFAULT '0',
  `fetchmsg` varchar(255) NOT NULL DEFAULT '',
  `inserttime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`indexid`),
  UNIQUE KEY `idx_keyword` (`indexkeyword`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name = "index_keywords")
@NamedQueries({
        @NamedQuery(name = "getWordByValue", query = "from IndexKeywords k where k.word = ?"),
        @NamedQuery(name = "getKeywordById", query = "from IndexKeywords k where k.indexId = ?")
})
public class IndexKeywords {
	@Id
	@GeneratedValue
	@Column(name = "indexid")
    private long indexId;
	
	@Column(name = "indexkeyword")
    private String word;
	
    @Column(name = "isactive")
    private int active;
    
    @Column(name = "inserttime")
    private Date insertTime;
    
    @Column(name = "updatetime")
    private Date updateTime;

    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
