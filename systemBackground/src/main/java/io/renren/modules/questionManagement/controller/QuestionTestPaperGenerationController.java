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

import io.renren.modules.questionManagement.entity.QuestionTestPaperGenerationEntity;
import io.renren.modules.questionManagement.service.QuestionTestPaperGenerationService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 试卷生成
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 16:38:29
 */
@RestController
@RequestMapping("questionManagement/questiontestpapergeneration")
public class QuestionTestPaperGenerationController {
    @Autowired
    private QuestionTestPaperGenerationService questionTestPaperGenerationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questiontestpapergeneration:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionTestPaperGenerationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseTitle}")
    @RequiresPermissions("questionManagement:questiontestpapergeneration:info")
    public R info(@PathVariable("courseTitle") Integer courseTitle){
			QuestionTestPaperGenerationEntity questionTestPaperGeneration = questionTestPaperGenerationService.selectById(courseTitle);

        return R.ok().put("questionTestPaperGeneration", questionTestPaperGeneration);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questiontestpapergeneration:save")
    public R save(@RequestBody QuestionTestPaperGenerationEntity questionTestPaperGeneration){
			questionTestPaperGenerationService.insert(questionTestPaperGeneration);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questiontestpapergeneration:update")
    public R update(@RequestBody QuestionTestPaperGenerationEntity questionTestPaperGeneration){
			questionTestPaperGenerationService.updateById(questionTestPaperGeneration);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questiontestpapergeneration:delete")
    public R delete(@RequestBody Integer[] courseTitles){
			questionTestPaperGenerationService.deleteBatchIds(Arrays.asList(courseTitles));

        return R.ok();
    }

}
