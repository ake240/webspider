package com.datayes.webspider.action.words;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.domain.words.IndexKeywords;
import com.datayes.webspider.domain.words.IndexTask;
import com.datayes.webspider.service.words.IKeywordService;
import com.datayes.webspider.service.words.IKeywordTaskService;

/**
 * Created by ruihua.huang on 2014/8/29.
 */
@ParentPackage("webspider")
@Namespace("/")
@Results({@Result(name = "page", location = "/WEB-INF/jsp/words/wordsTask.jsp"),
        @Result(name = "ajax", location = "/WEB-INF/jsp/words/table.jsp"),
        @Result(name = "task", location = "/WEB-INF/jsp/words/taskDetail.jsp"),
        @Result(name = "json", type = "json"),
        @Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),

        //       @Result(name = "success", location = "/WEB-INF/jsp/words/taskDetail.jsp"),
})
public class WordsTaskAction extends BaseAction {
    @Action("wordsTask")
    public String wordsTask() {
        pageDTO = taskService.getTasks(params, pageNum, PageSizeConstants.WORD_TASK_PAGE_SIZE);
        if (isAjax)
            return "ajax";
        else {
            return "page";
        }
    }

    @Action("findFetchWords")
    public String findFetchWords() {
        List<IndexKeywords> list = keywordService.getAllWords();
        Map<Long, String> pair = new HashMap<Long, String>();
        for (IndexKeywords w : list) {
            pair.put(w.getIndexId(), w.getWord());
        }
        data = JSONObject.toJSON(pair);
        return "json";
    }

    @Action("createTask")
    public String createTask() {
        if (task == null)
            return "task";
        else {
            task = taskService.getTaskById(task);
            return "task";
        }
    }

    @Action("addWordsTask")
    public String addWordsTask() {
        taskService.addWordTask(task);
        return "redirect2success";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public PageDTO getPageDTO() {
        return pageDTO;
    }

    public void setPageDTO(PageDTO pageDTO) {
        this.pageDTO = pageDTO;
    }

    public IndexTask getTask() {
        return task;
    }

    public void setTask(IndexTask task) {
        this.task = task;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Boolean getIsAjax() {
        return isAjax;
    }

    public void setIsAjax(Boolean isAjax) {
        this.isAjax = isAjax;
    }

    private PageDTO pageDTO;
    private Object data;
    private Map<String, String> params;
    private IndexTask task;
    private Boolean isAjax = false;
    private int pageNum = 1;
    @Resource
    private IKeywordTaskService taskService;
    @Resource
    private IKeywordService keywordService;
}
