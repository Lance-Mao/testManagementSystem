package io.renren.modules.area.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.area.entity.CollegeEntity;

import java.util.Map;

/**
 * 高校
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
public interface CollegeService extends IService<CollegeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

