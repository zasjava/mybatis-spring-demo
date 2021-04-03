package com.mybatis.spring.goods.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mybatis.spring.goods.pojo.TbItem;
import com.mybatis.spring.goods.pojo.TbItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemMapper extends BaseMapper<TbItem> {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    int updateGoodsBatchById(@Param("idArray") String[] idArray, @Param("goodStatus") Byte goodStatus);
}