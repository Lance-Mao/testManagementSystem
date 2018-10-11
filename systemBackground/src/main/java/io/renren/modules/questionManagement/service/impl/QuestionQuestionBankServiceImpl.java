package io.renren.modules.questionManagement.service.impl;

import io.renren.modules.questionManagement.entity.QuestionKnowledgePointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionQuestionBankDao;
import io.renren.modules.questionManagement.entity.QuestionQuestionBankEntity;
import io.renren.modules.questionManagement.service.QuestionQuestionBankService;


@Service("questionQuestionBankService")
public class QuestionQuestionBankServiceImpl extends ServiceImpl<QuestionQuestionBankDao, QuestionQuestionBankEntity> implements QuestionQuestionBankService {

    @Autowired
    private QuestionQuestionBankDao questionQuestionBankDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Map<String, Object> pageInfo = PageUtils.getPageInfo(params);

        List<Map<String, Object>> list = questionQuestionBankDao.selectPage(pageInfo);
        int totalCount = questionQuestionBankDao.selectTotalCount(pageInfo);

        return new PageUtils(list, totalCount, (Integer) pageInfo.get("limit"), (Integer) pageInfo.get("page"));
    }

    @Override
    public void insertQuestionBank(List<Map<String, Object>> bankData) {
        List<QuestionQuestionBankEntity> bankDataList = new ArrayList<>();
        List<Map<String, Object>> mapList;
        for (Map<String, Object> map : bankData) {
            QuestionQuestionBankEntity questionQuestionBankEntity = new QuestionQuestionBankEntity();
            questionQuestionBankEntity.setCourseTitleId(Integer.parseInt(map.get("courseTitleId").toString()));
            questionQuestionBankEntity.setKnowledgeTitleId(Long.valueOf(map.get("knowledgeTitleId").toString()));
            questionQuestionBankEntity.setQuestionType(map.get("typeContent").toString());
            questionQuestionBankEntity.setEnterer(Long.valueOf(map.get("enterer").toString()));
            mapList = (List<Map<String, Object>>) map.get("questionDetails");
            for (Map<String, Object> map_o : mapList) {
                questionQuestionBankEntity.setTitleDescription(map_o.get("content").toString());
//                questionQuestionBankEntity.setAnswer(map_o.get("answer").toString());
//                questionQuestionBankEntity.setAnswerA(map_o.get("answer_A").toString());
//                questionQuestionBankEntity.setAnswerB(map_o.get("answer_B").toString());
//                questionQuestionBankEntity.setAnswerC(map_o.get("answer_C").toString());
//                questionQuestionBankEntity.setAnswerD(map_o.get("answer_D").toString());
//                questionQuestionBankEntity.setAnswerE(map_o.get("answer_E").toString());
//                questionQuestionBankEntity.setAnswerF(map_o.get("answer_F").toString());
                bankDataList.add(questionQuestionBankEntity);
            }
        }
        System.out.println(bankDataList + "打印数据");
        this.insertBatch(bankDataList);
    }

}
