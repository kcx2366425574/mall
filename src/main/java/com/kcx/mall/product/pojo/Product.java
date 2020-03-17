package com.kcx.mall.product.pojo;

import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.shop.pojo.Shop;

public class Product {
    private Integer proId;

    private String proName;

    private Float proPrice;

    private String proInfo;

    private Customer proCus;

    private Shop proShop;

    private Integer proPtId;

    private String proState;

    private Integer proManaId;

    private String proPic;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public Float getProPrice() {
        return proPrice;
    }

    public void setProPrice(Float proPrice) {
        this.proPrice = proPrice;
    }

    public String getProInfo() {
        return proInfo;
    }

    public void setProInfo(String proInfo) {
        this.proInfo = proInfo == null ? null : proInfo.trim();
    }

    public Customer getProCus() {
        return proCus;
    }

    public void setProCus(Customer proCus) {
        this.proCus = proCus;
    }

    public Shop getProShop() {
        return proShop;
    }

    public void setProShop(Shop proShop) {
        this.proShop = proShop;
    }

    public Integer getProPtId() {
        return proPtId;
    }

    public void setProPtId(Integer proPtId) {
        this.proPtId = proPtId;
    }

    public String getProState() {
        return proState;
    }

    public void setProState(String proState) {
        this.proState = proState == null ? null : proState.trim();
    }

    public Integer getProManaId() {
        return proManaId;
    }

    public void setProManaId(Integer proManaId) {
        this.proManaId = proManaId;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic == null ? null : proPic.trim();
    }
}