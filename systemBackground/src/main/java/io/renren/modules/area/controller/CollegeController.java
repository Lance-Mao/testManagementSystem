package io.renren.modules.area.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.area.entity.CollegeEntity;
import io.renren.modules.area.service.CollegeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 高校
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
@RestController
@RequestMapping("area/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("area:college:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collegeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{coid}")
    @RequiresPermissions("area:college:info")
    public R info(@PathVariable("coid") Integer coid){
			CollegeEntity college = collegeService.selectById(coid);

        return R.ok().put("college", college);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("area:college:save")
    public R save(@RequestBody CollegeEntity college){
			collegeService.insert(college);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("area:college:update")
    public R update(@RequestBody CollegeEntity college){
			collegeService.updateById(college);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("area:college:delete")
    public R delete(@RequestBody Integer[] coids){
			collegeService.deleteBatchIds(Arrays.asList(coids));

        return R.ok();
    }

}
