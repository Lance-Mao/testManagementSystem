package io.renren.modules.questionManagement.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.questionManagement.entity.QuestionQuestionTypeEntity;
import io.renren.modules.questionManagement.service.QuestionQuestionTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 题型
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@RestController
@RequestMapping("questionManagement/questionquestiontype")
public class QuestionQuestionTypeController {
    @Autowired
    private QuestionQuestionTypeService questionQuestionTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questionquestiontype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionQuestionTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("questionManagement:questionquestiontype:info")
    public R info(@PathVariable("id") Integer id){
			QuestionQuestionTypeEntity questionQuestionType = questionQuestionTypeService.selectById(id);

        return R.ok().put("questionQuestionType", questionQuestionType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questionquestiontype:save")
    public R save(@RequestBody QuestionQuestionTypeEntity questionQuestionType){
			questionQuestionTypeService.insert(questionQuestionType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questionquestiontype:update")
    public R update(@RequestBody QuestionQuestionTypeEntity questionQuestionType){
			questionQuestionTypeService.updateById(questionQuestionType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questionquestiontype:delete")
    public R delete(@RequestBody Integer[] ids){
			questionQuestionTypeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
