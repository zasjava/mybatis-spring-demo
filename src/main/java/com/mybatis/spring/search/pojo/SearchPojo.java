package com.mybatis.spring.search.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/29 0029.
 */
public class SearchPojo implements Serializable{

    private String id;
    private String title;
    private long price;
    private String sellPoint;
    private String image;
    private String catName;
    private String itemDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getImage() {
        return image;
    }

    public  String[] getImages(){
        String[] images = this.getImage().split(",");
        return  images;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
