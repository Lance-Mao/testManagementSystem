package io.renren.modules.area.dao;

import io.renren.modules.area.entity.CollegeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 高校
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
@Mapper
public interface CollegeDao extends BaseMapper<CollegeEntity> {

    List<Map<String,Object>> selectByProvinceId(int params);
}
