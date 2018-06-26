package io.renren.modules.area.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.area.entity.RegionProvinceEntity;

import java.util.List;
import java.util.Map;

/**
 * 省份
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
public interface RegionProvinceService extends IService<RegionProvinceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> selectAll();
}

