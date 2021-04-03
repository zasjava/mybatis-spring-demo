package com.mybatis.spring.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mybatis.spring.common.responseUtil.DatagridResult;
import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.enums.ItemStatusEnum;
import com.mybatis.spring.goods.pojo.TbItem;
import com.mybatis.spring.goods.service.ItemService;
import com.mybatis.spring.goods.vo.TbItemVo;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品信息接口
 */
@Controller
@RequestMapping("/good")
public class ItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    /**
     * 根据商品ID，查找商品信息
     *
     * @param itemId 商品ID
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    /**
     * 获取商品列表
     *
     * @param page 页数
     * @param rows 行数
     * @return
     */
    @RequestMapping("/items")
    @ResponseBody
    public DatagridResult getItemList(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "30") Integer rows) {
        return itemService.getItemList(page, rows);
    }

    /**
     * 保存商品信息
     *
     * @param tbItem     商品信息pojo
     * @param desc       商品详情信息字段
     * @param itemParams 商品参数字符串
     * @return
     * @throws Exception
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public ResponseResult saveItem(TbItem tbItem, String desc, String itemParams) throws Exception {
        return itemService.saveItem(tbItem, desc, itemParams);
    }

    /**
     *  下载excel
     * @param response
     */
    @RequestMapping("/downGoodsExcel")
    public void downGoodsExcel(HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        LOGGER.debug("开始时间：" + startTime);
        //查询所有的商品信息
        List<TbItem> tbItems = itemService.selectList(new EntityWrapper<TbItem>().orderBy("updated").last("desc"));
        List<TbItemVo> tbItemVos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {//制作数据
            tbItems.parallelStream().forEach(tbItem -> {
                TbItemVo tbItemVo = new TbItemVo();
                tbItemVo.setId(tbItem.getId());
                tbItemVo.setUpdateTimeFormatStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbItem.getUpdated()));
                tbItemVo.setCreateTimeFormatStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbItem.getCreated()));
                int statusFlag = Integer.parseInt(String.valueOf(tbItem.getStatus()));
                String statusStr = null;
                if (statusFlag == ItemStatusEnum.NORMAL.getStatus()) {
                    statusStr = ItemStatusEnum.NORMAL.getValue();
                } else if (statusFlag == ItemStatusEnum.UP.getStatus()) {
                    statusStr = ItemStatusEnum.UP.getValue();
                } else if (statusFlag == ItemStatusEnum.DELETE.getStatus()) {
                    statusStr = ItemStatusEnum.DELETE.getValue();
                } else {
                    statusStr = "";
                }
                tbItemVo.setStatusStr(statusStr);
                tbItemVo.setBarcode(tbItem.getBarcode());
                tbItemVo.setImage(tbItem.getImage());
                tbItemVo.setCid(tbItem.getCid());
                tbItemVo.setNum(tbItem.getNum());
                tbItemVo.setPrice(tbItem.getPrice());
                tbItemVo.setSellPoint(tbItem.getSellPoint());
                tbItemVo.setTitle(tbItem.getTitle());
                tbItemVos.add(tbItemVo);
            });
        }
        Map map = new HashMap();
        map.put("goods", tbItemVos);
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("excel_template/goods_template.xls");
            XLSTransformer xlsTransformer = new XLSTransformer();
            Workbook workbook = xlsTransformer.transformXLS(is, map);
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("商品信息.xls", "UTF-8"));
            // 写出文件
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            // 输出
            workbook.write(os);
            long endTime = System.currentTimeMillis();
            LOGGER.debug("开始时间：" + endTime);
            LOGGER.debug("花费时间：" + (endTime - startTime));
            is.close();
            os.flush();
            os.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     *  更改商品状态
     * @param ids 商品ids
     * @param goodStatus 商品状态 1 正常 2 下架 3 删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeStatus")
    public ResponseResult changeGoodsStatus (@RequestParam("ids") String ids,@RequestParam("goodStatus")String goodStatus ){
        ResponseResult responseResult = new ResponseResult();
        List<TbItem> tbItemList = new ArrayList<>();
        boolean flag = itemService.updateGoodsBatchById(ids.trim().split(","),goodStatus);
        if(flag){
            responseResult.setMsg("修改成功");
            responseResult.setStatus(200);
        }else {
            responseResult.setMsg("未保存成功，请稍后重试！");
            responseResult.setStatus(400);
        }
        return responseResult;
    }
}
