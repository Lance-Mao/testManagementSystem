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

import io.renren.modules.area.entity.RegionAreaEntity;
import io.renren.modules.area.service.RegionAreaService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 区县
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
@RestController
@RequestMapping("area/regionarea")
public class RegionAreaController {
    @Autowired
    private RegionAreaService regionAreaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("area:regionarea:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = regionAreaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{aid}")
    @RequiresPermissions("area:regionarea:info")
    public R info(@PathVariable("aid") Integer aid){
			RegionAreaEntity regionArea = regionAreaService.selectById(aid);

        return R.ok().put("regionArea", regionArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("area:regionarea:save")
    public R save(@RequestBody RegionAreaEntity regionArea){
			regionAreaService.insert(regionArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("area:regionarea:update")
    public R update(@RequestBody RegionAreaEntity regionArea){
			regionAreaService.updateById(regionArea);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("area:regionarea:delete")
    public R delete(@RequestBody Integer[] aids){
			regionAreaService.deleteBatchIds(Arrays.asList(aids));

        return R.ok();
    }

}
