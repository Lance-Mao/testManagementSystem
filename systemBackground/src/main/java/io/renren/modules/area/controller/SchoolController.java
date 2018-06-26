package io.renren.modules.area.controller;

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

import io.renren.modules.area.entity.SchoolEntity;
import io.renren.modules.area.service.SchoolService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 学院
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
@RestController
@RequestMapping("area/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("area:school:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = schoolService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/lists")
    @RequiresPermissions("area:school:list")
    public R lists(@RequestParam Integer id) {
        List<Map<String,Object>> schools = schoolService.selectByCollegeId(id);

        return R.ok().put("schools", schools);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{scid}")
    @RequiresPermissions("area:school:info")
    public R info(@PathVariable("scid") Integer scid){
			SchoolEntity school = schoolService.selectById(scid);

        return R.ok().put("school", school);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("area:school:save")
    public R save(@RequestBody SchoolEntity school){
			schoolService.insert(school);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("area:school:update")
    public R update(@RequestBody SchoolEntity school){
			schoolService.updateById(school);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("area:school:delete")
    public R delete(@RequestBody Integer[] scids){
			schoolService.deleteBatchIds(Arrays.asList(scids));

        return R.ok();
    }

}
