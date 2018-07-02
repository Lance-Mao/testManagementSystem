package io.renren.modules.questionManagement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.questionManagement.entity.QuestionKnowledgePointEntity;
import io.renren.modules.questionManagement.service.QuestionKnowledgePointService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 知识点
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@RestController
@RequestMapping("questionManagement/questionknowledgepoint")
public class QuestionKnowledgePointController {
    @Autowired
    private QuestionKnowledgePointService questionKnowledgePointService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questionknowledgepoint:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = questionKnowledgePointService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据对应章节id获取相关知识点
     */
    @RequestMapping("/lists")
    @RequiresPermissions("questionManagement:questionknowledgepoint:list")
    public R lists(@RequestParam Map<String, Object> params) {
        List<Map<String,Object>> list =  questionKnowledgePointService.selectByChapterId(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("questionManagement:questionknowledgepoint:info")
    public R info(@PathVariable("id") Long id) {
        QuestionKnowledgePointEntity questionKnowledgePoint = questionKnowledgePointService.selectById(id);

        return R.ok().put("questionKnowledgePoint", questionKnowledgePoint);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questionknowledgepoint:save")
    public R save(@RequestBody QuestionKnowledgePointEntity questionKnowledgePoint) {
        questionKnowledgePoint.setCreator(ShiroUtils.getUserId());
        questionKnowledgePointService.insert(questionKnowledgePoint);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questionknowledgepoint:update")
    public R update(@RequestBody QuestionKnowledgePointEntity questionKnowledgePoint) {
        questionKnowledgePointService.updateById(questionKnowledgePoint);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questionknowledgepoint:delete")
    public R delete(@RequestBody Long[] ids) {
        questionKnowledgePointService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
