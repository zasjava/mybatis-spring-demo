package com.mybatis.spring.common.responseUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */
public class DatagridResult implements Serializable {
    private  long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
