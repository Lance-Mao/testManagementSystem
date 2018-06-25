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

import io.renren.modules.area.entity.RegionCityEntity;
import io.renren.modules.area.service.RegionCityService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 城市
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
@RestController
@RequestMapping("area/regioncity")
public class RegionCityController {
    @Autowired
    private RegionCityService regionCityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("area:regioncity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = regionCityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cid}")
    @RequiresPermissions("area:regioncity:info")
    public R info(@PathVariable("cid") Integer cid){
			RegionCityEntity regionCity = regionCityService.selectById(cid);

        return R.ok().put("regionCity", regionCity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("area:regioncity:save")
    public R save(@RequestBody RegionCityEntity regionCity){
			regionCityService.insert(regionCity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("area:regioncity:update")
    public R update(@RequestBody RegionCityEntity regionCity){
			regionCityService.updateById(regionCity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("area:regioncity:delete")
    public R delete(@RequestBody Integer[] cids){
			regionCityService.deleteBatchIds(Arrays.asList(cids));

        return R.ok();
    }

}
