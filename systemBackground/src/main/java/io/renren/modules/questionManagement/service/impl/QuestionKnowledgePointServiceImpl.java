package io.renren.modules.questionManagement.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionKnowledgePointDao;
import io.renren.modules.questionManagement.entity.QuestionKnowledgePointEntity;
import io.renren.modules.questionManagement.service.QuestionKnowledgePointService;


@Service("questionKnowledgePointService")
public class QuestionKnowledgePointServiceImpl extends ServiceImpl<QuestionKnowledgePointDao, QuestionKnowledgePointEntity> implements QuestionKnowledgePointService {

    @Autowired
    private QuestionKnowledgePointDao questionKnowledgePointDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Map<String, Object> pageInfo = new HashMap<>();
        String key = (String) params.get("key");

        int page = Integer.valueOf((String) params.get("page"));
        int limit = Integer.valueOf((String) params.get("limit"));

        if (StringUtils.isNotBlank(key)) {
            pageInfo.put("key", key);
        }else {
            pageInfo.put("key", "");
        }
        pageInfo.put("page", page);
        pageInfo.put("limit", limit);

        List<Map<String, Object>> list = questionKnowledgePointDao.selectPage(pageInfo);
        int totalCount = questionKnowledgePointDao.selectTotalCount();

        return new PageUtils(list, totalCount, limit, page);
    }

}
