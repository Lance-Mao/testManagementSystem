package io.renren.modules.questionManagement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.questionManagement.entity.QuestionCourseTitleEntity;
import io.renren.modules.questionManagement.service.QuestionCourseTitleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程名称
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@RestController
@RequestMapping("questionManagement/questioncoursetitle")
public class QuestionCourseTitleController {
    @Autowired
    private QuestionCourseTitleService questionCourseTitleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questioncoursetitle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionCourseTitleService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询全部数据
     */
    @RequestMapping("/lists")
    @RequiresPermissions("questionManagement:questioncoursetitle:list")
    public R lists(){
        List<Map<String,Object>> list = questionCourseTitleService.selectAll();

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("questionManagement:questioncoursetitle:info")
    public R info(@PathVariable("id") Integer id){
			QuestionCourseTitleEntity questionCourseTitle = questionCourseTitleService.selectById(id);

        return R.ok().put("questionCourseTitle", questionCourseTitle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questioncoursetitle:save")
    public R save(@RequestBody QuestionCourseTitleEntity questionCourseTitle){
			questionCourseTitleService.insert(questionCourseTitle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questioncoursetitle:update")
    public R update(@RequestBody QuestionCourseTitleEntity questionCourseTitle){
			questionCourseTitleService.updateById(questionCourseTitle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questioncoursetitle:delete")
    public R delete(@RequestBody Integer[] ids){
			questionCourseTitleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
