package io.renren.modules.area.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.area.entity.SchoolEntity;

import java.util.List;
import java.util.Map;

/**
 * 学院
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
public interface SchoolService extends IService<SchoolEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> selectByCollegeId(Integer id);
}

