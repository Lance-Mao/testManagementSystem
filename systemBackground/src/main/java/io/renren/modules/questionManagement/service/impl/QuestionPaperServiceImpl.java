package io.renren.modules.questionManagement.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.dao.QuestionKnowledgePointDao;
import io.renren.modules.questionManagement.dao.QuestionPaperDao;
import io.renren.modules.questionManagement.entity.QuestionPaperEntity;
import io.renren.modules.questionManagement.service.QuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("questionPaperService")
public class QuestionPaperServiceImpl extends ServiceImpl<QuestionPaperDao, QuestionPaperEntity> implements QuestionPaperService {

    @Autowired
    private QuestionPaperDao questionPaperDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Map<String, Object> pageInfo = PageUtils.getPageInfo(params);

        List<Map<String, Object>> list = questionPaperDao.selectPage(pageInfo);
        int totalCount = questionPaperDao.selectTotalCount(pageInfo);

        return new PageUtils(list, totalCount, (Integer) pageInfo.get("limit"), (Integer) pageInfo.get("page"));
    }
}
