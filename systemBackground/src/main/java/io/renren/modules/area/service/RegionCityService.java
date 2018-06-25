package io.renren.modules.area.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.area.entity.RegionCityEntity;

import java.util.Map;

/**
 * 城市
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
public interface RegionCityService extends IService<RegionCityEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

