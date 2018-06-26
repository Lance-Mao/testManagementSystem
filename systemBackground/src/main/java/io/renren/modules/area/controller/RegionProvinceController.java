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

import io.renren.modules.area.entity.RegionProvinceEntity;
import io.renren.modules.area.service.RegionProvinceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 省份
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
@RestController
@RequestMapping("area/regionprovince")
public class RegionProvinceController {
    @Autowired
    private RegionProvinceService regionProvinceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("area:regionprovince:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = regionProvinceService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping("/lists")
    @RequiresPermissions("area:regionprovince:list")
    public R lists(){
        List<Map<String,Object>> province = regionProvinceService.selectAll();

        return R.ok().put("province", province);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{pid}")
    @RequiresPermissions("area:regionprovince:info")
    public R info(@PathVariable("pid") Integer pid){
			RegionProvinceEntity regionProvince = regionProvinceService.selectById(pid);

        return R.ok().put("regionProvince", regionProvince);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("area:regionprovince:save")
    public R save(@RequestBody RegionProvinceEntity regionProvince){
			regionProvinceService.insert(regionProvince);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("area:regionprovince:update")
    public R update(@RequestBody RegionProvinceEntity regionProvince){
			regionProvinceService.updateById(regionProvince);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("area:regionprovince:delete")
    public R delete(@RequestBody Integer[] pids){
			regionProvinceService.deleteBatchIds(Arrays.asList(pids));

        return R.ok();
    }

}
