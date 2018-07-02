package io.renren.modules.questionManagement.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.dao.QuestionKnowledgePointDao;
import io.renren.modules.questionManagement.entity.QuestionKnowledgePointEntity;
import io.renren.modules.questionManagement.service.QuestionKnowledgePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("questionKnowledgePointService")
public class QuestionKnowledgePointServiceImpl extends ServiceImpl<QuestionKnowledgePointDao, QuestionKnowledgePointEntity> implements QuestionKnowledgePointService {

    @Autowired
    private QuestionKnowledgePointDao questionKnowledgePointDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Map<String, Object> pageInfo = PageUtils.getPageInfo(params);

        List<Map<String, Object>> list = questionKnowledgePointDao.selectPage(pageInfo);
        int totalCount = questionKnowledgePointDao.selectTotalCount(pageInfo);

        return new PageUtils(list, totalCount, (Integer) pageInfo.get("limit"), (Integer) pageInfo.get("page"));
    }

    @Override
    public List<Map<String, Object>> selectByChapterId(Map<String, Object> params) {
        String isChild = (String) params.get("isChild");
        int id = Integer.parseInt(params.get("id").toString());
        List<Map<String, Object>> datas = questionKnowledgePointDao.selectAll(id);
        if (isChild.equals("yes")) {
            for (Map<String,Object> data : datas) {
                data.put("children", new ArrayList<>());
            }
        }

        return datas;
    }


}
