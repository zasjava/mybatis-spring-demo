package com.mybatis.spring.content.service.impl;

import com.mybatis.spring.common.responseUtil.TreeResult;
import com.mybatis.spring.content.mapper.TbContentCategoryMapper;
import com.mybatis.spring.content.pojo.TbContentCategory;
import com.mybatis.spring.content.pojo.TbContentCategoryExample;
import com.mybatis.spring.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TreeResult> getContentCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //查询结果
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        List<TreeResult>  resultList = new ArrayList<>();
        //封装Treeresult
        for (TbContentCategory contentCategory: list){
            TreeResult result = new TreeResult();
            result.setId(contentCategory.getId());
            result.setText(contentCategory.getName());
            result.setState(contentCategory.getIsParent()?"closed":"open");
            resultList.add(result);
        }
        return resultList;
    }

    //保存页子节点
    @Transactional
    @Override
    public void saveContentCatory(long parentId,String name) {
       /* --1.添加节点的时候需要做:
        2.把当前节点设置成为叶子节点
        */
        //补全数据
        TbContentCategory contentCategory  = new TbContentCategory();
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategory.setSortOrder(1);//排序
        contentCategory.setStatus(1);//状态
        contentCategory.setIsParent(false);//设置当前节点为子节点
        contentCategory.setName(name);//名称
        contentCategory.setParentId(parentId);//选中节点的id
        tbContentCategoryMapper.insert(contentCategory);

        //3.判断当前选中的节点是否为叶子节点, 如果是父节点,不做修改,否则,把当前选中的节点变成父节点  修改isparent 1 0
        TbContentCategory  parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parentNode.getIsParent()){//选中的节点不是父节点
            parentNode.setIsParent(true);//改改为父节点
            tbContentCategoryMapper.updateByPrimaryKey(parentNode);//执行修改
        }
    }


}
