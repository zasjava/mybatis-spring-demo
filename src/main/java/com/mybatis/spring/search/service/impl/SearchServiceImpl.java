package com.mybatis.spring.search.service.impl;


import com.mybatis.spring.common.responseUtil.ResponseResult;
import com.mybatis.spring.search.service.SearchService;
import com.mybatis.spring.search.pojo.SearchPojo;
import com.mybatis.spring.search.pojo.SearchResult;
import com.mybatis.spring.search.mapper.SearchMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/29 0029.
 */
@Service
public class SearchServiceImpl implements SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private SolrServer  solrServer;

    @Override
    public ResponseResult importItemIndex() {
        //查询数据库
        List<SearchPojo>   list = searchMapper.getItemSearch();
        //把list集合里的数据遍历出来,放到索引库中.
        LOGGER.info("调用搜索SearchServiceImpl里的方法开始");
        try {
            //循环数据添加到索引库
            for (SearchPojo  item: list){
                SolrInputDocument   doc = new SolrInputDocument();
                doc.addField("id",item.getId());
                doc.addField("item_title",item.getTitle());
                doc.addField("item_sell_point",item.getSellPoint());
                doc.addField("item_price",item.getPrice());
                doc.addField("item_image",item.getImage());
                doc.addField("item_category_name",item.getCatName());
                doc.addField("item_desc",item.getItemDesc());
                System.out.println(doc.size());
                solrServer.add(doc);
                solrServer.commit();
            }

            return ResponseResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("系统发生异常",e);
            return null;
        }
    }
    //搜索功能
    @Override
    public SearchResult searchItem(String queryName, Integer pageNow, Integer pageSize) {
        //根据搜索数据,查询内容
        SolrQuery  solrQuery = new SolrQuery();
        solrQuery.set("df","item_keywords");//设置默认搜索域
        if (StringUtils.isNotBlank(queryName)){
            solrQuery.setQuery(queryName);
        }else {
            solrQuery.setQuery("*:*");
        }
        //高亮显示
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        solrQuery.addHighlightField("item_title");//显示字段
        //分页信息
        //3.计算总页数

        if(pageNow<0)  pageNow=1;
        if (pageNow==0) pageNow=1;
        //4.计算当前页
        int pageNows= (pageNow-1)*pageSize;
        solrQuery.setStart(pageNows);
        solrQuery.setRows(pageSize);
        List<SearchPojo> list = getitemIndexList(solrQuery);
        SearchResult result = new SearchResult();
        result.setItemList(list);
        result.setCurPage(pageNow);
        result.setRecordCount(list.size());
        //总页数
        int totalPage= (list.size()+pageSize-1)/pageSize;
        result.setPageCount(totalPage);
        return result;
    }

    private List<SearchPojo> getitemIndexList(SolrQuery solrQuery) {
        try {
            //从索引库中查询商品信息
            QueryResponse response = solrServer.query(solrQuery);
            SolrDocumentList solrDocuments = response.getResults();
            List<SearchPojo>  resultlist = new ArrayList<>();
            for (SolrDocument solrDocument:solrDocuments){
                SearchPojo searchPojo = new SearchPojo();
                searchPojo.setId((String) solrDocument.get("id"));
                searchPojo.setImage((String) solrDocument.get("item_image"));
                searchPojo.setCatName((String) solrDocument.get("item_category_name"));
                searchPojo.setItemDesc((String) solrDocument.get("item_desc"));
                //取高亮显示
                Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
                List<String> strings = highlighting.get(solrDocument.get("id")).get("item_title");
                if (strings !=null && strings.size()>0){
                    searchPojo.setTitle(strings.get(0));
                }else {
                    searchPojo.setTitle((String) solrDocument.get("item_title"));
                }
                searchPojo.setSellPoint((String) solrDocument.get("item_sell_point"));
                searchPojo.setPrice((Long) solrDocument.get("item_price"));
                resultlist.add(searchPojo);
            }
            return  resultlist;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public ResponseResult sysnIndex(long itemId) {
        SearchPojo item = searchMapper.getItemById(itemId);
        try {
              //循环数据添加到索引库
                SolrInputDocument   doc = new SolrInputDocument();
                doc.addField("id",item.getId());
                doc.addField("item_title",item.getTitle());
                doc.addField("item_sell_point",item.getSellPoint());
                doc.addField("item_price",item.getPrice());
                doc.addField("item_image",item.getImage());
                doc.addField("item_category_name",item.getCatName());
                doc.addField("item_desc",item.getItemDesc());
                solrServer.add(doc);
                solrServer.commit();
            return ResponseResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("系统发生异常",e);
            return null;
        }

    }
}
