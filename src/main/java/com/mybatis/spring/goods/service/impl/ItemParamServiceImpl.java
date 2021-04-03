package com.mybatis.spring.goods.service.impl;


import com.mybatis.spring.common.jsonUtil.JsonUtils;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.goods.mapper.TbItemParamItemMapper;
import com.mybatis.spring.goods.mapper.TbItemParamMapper;
import com.mybatis.spring.goods.pojo.TbItemParam;
import com.mybatis.spring.goods.pojo.TbItemParamExample;
import com.mybatis.spring.goods.pojo.TbItemParamItem;
import com.mybatis.spring.goods.pojo.TbItemParamItemExample;
import com.mybatis.spring.goods.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/23.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    //查询列表
    @Override
    public List<TbItemParam> queryByListParam() {
        List<TbItemParam> list = itemParamMapper.queryCatItemParam();
        return list;
    }

    //根据商品类目查询规格参数
    @Override
    public TbItemParam queryByCidParam(long cid) {
        TbItemParamExample  example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(example);
        if (tbItemParams!=null && tbItemParams.size()>0){
            return  tbItemParams.get(0);
        }
        return null;
    }
    //保存规格参数模板
    @Transactional
    @Override
    public ResponseResult saveItemParam(TbItemParam tbItemParam) {
        //s数据补全
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        itemParamMapper.insert(tbItemParam);
        return ResponseResult.ok();
    }
    //根据商品id查询商品的规格参数
    @Override
    public String getByItemIdParamData(long itemId) {
        TbItemParamItemExample  example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list ==null || list.size()==0){
            return null;
        }
        //获取对象
        TbItemParamItem  tbItemParamItem = list.get(0);
        //获取json数据
        String paramData = tbItemParamItem.getParamData();
        //遍历json数据
        //生成html
        // 把规格参数json数据转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb= new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

}
