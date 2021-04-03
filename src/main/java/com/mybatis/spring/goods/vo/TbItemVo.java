package com.mybatis.spring.goods.vo;

import com.mybatis.spring.goods.pojo.TbItem;

public class TbItemVo extends TbItem {
    private String statusStr;
    private String createTimeFormatStr;
    private String updateTimeFormatStr;

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getCreateTimeFormatStr() {
        return createTimeFormatStr;
    }

    public void setCreateTimeFormatStr(String createTimeFormatStr) {
        this.createTimeFormatStr = createTimeFormatStr;
    }

    public String getUpdateTimeFormatStr() {
        return updateTimeFormatStr;
    }

    public void setUpdateTimeFormatStr(String updateTimeFormatStr) {
        this.updateTimeFormatStr = updateTimeFormatStr;
    }
}
