package com.mybatis.spring.search.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */
public class SearchResult implements Serializable {
    private List<SearchPojo> itemList;
    //总记录数
    private long recordCount;
    //总页数
    private long pageCount;
    //当前页
    private long curPage;

    public List<SearchPojo> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchPojo> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
