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

import io.renren.modules.questionManagement.entity.QuestionQuestionBankEntity;
import io.renren.modules.questionManagement.service.QuestionQuestionBankService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 题库
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-27 14:35:40
 */
@RestController
@RequestMapping("questionManagement/questionquestionbank")
public class QuestionQuestionBankController {
    @Autowired
    private QuestionQuestionBankService questionQuestionBankService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questionquestionbank:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionQuestionBankService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("questionManagement:questionquestionbank:info")
    public R info(@PathVariable("id") Long id){
			QuestionQuestionBankEntity questionQuestionBank = questionQuestionBankService.selectById(id);

        return R.ok().put("questionQuestionBank", questionQuestionBank);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questionquestionbank:save")
    public R save(@RequestBody QuestionQuestionBankEntity questionQuestionBank){
			questionQuestionBankService.insert(questionQuestionBank);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questionquestionbank:update")
    public R update(@RequestBody QuestionQuestionBankEntity questionQuestionBank){
			questionQuestionBankService.updateById(questionQuestionBank);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questionquestionbank:delete")
    public R delete(@RequestBody Long[] ids){
			questionQuestionBankService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
