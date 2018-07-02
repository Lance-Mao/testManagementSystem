package io.renren.modules.questionManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionChapterDao;
import io.renren.modules.questionManagement.entity.QuestionChapterEntity;
import io.renren.modules.questionManagement.service.QuestionChapterService;


@Service("questionChapterService")
public class QuestionChapterServiceImpl extends ServiceImpl<QuestionChapterDao, QuestionChapterEntity> implements QuestionChapterService {

    @Autowired
    private QuestionChapterDao questionChapterDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionChapterEntity> page = this.selectPage(
                new Query<QuestionChapterEntity>(params).getPage(),
                new EntityWrapper<QuestionChapterEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> listByCourseTitle(Map<String, Object> params) {
        String isChild = (String) params.get("isChild");
        int id = Integer.parseInt(params.get("id").toString());
        List<Map<String, Object>> datas = questionChapterDao.selectAll(id);
        if (isChild.equals("yes")) {
            for (Map<String,Object> data : datas) {
                data.put("children", new ArrayList<>());
            }
        }

        return datas;
    }

}
