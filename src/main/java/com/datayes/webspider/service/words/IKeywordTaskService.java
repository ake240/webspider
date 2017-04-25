package com.datayes.webspider.service.words;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.words.IndexTask;

import java.util.Map;

public interface IKeywordTaskService {

    public void addKeywordTask(String word);

    public void addWordTask(IndexTask task);

    public IndexTask getTaskById(IndexTask task);

    PageDTO getTasks(Map<String, String> params, int page, int defaultSize);
}
