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

import io.renren.modules.questionManagement.entity.QuestionChapterEntity;
import io.renren.modules.questionManagement.service.QuestionChapterService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 章节
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 21:37:39
 */
@RestController
@RequestMapping("questionManagement/questionchapter")
public class QuestionChapterController {
    @Autowired
    private QuestionChapterService questionChapterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questionchapter:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionChapterService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("questionManagement:questionchapter:info")
    public R info(@PathVariable("id") Long id){
			QuestionChapterEntity questionChapter = questionChapterService.selectById(id);

        return R.ok().put("questionChapter", questionChapter);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questionchapter:save")
    public R save(@RequestBody QuestionChapterEntity questionChapter){
			questionChapterService.insert(questionChapter);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questionchapter:update")
    public R update(@RequestBody QuestionChapterEntity questionChapter){
			questionChapterService.updateById(questionChapter);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questionchapter:delete")
    public R delete(@RequestBody Long[] ids){
			questionChapterService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
