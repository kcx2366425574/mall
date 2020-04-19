package com.kcx.mall.pic.pojo;

import com.kcx.mall.product.pojo.Product;

public class Pic {
    private Integer picId;

    private Product picPro;

    private String picInfo;

    //getters and setters
    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public Product getPicPro() {
        return picPro;
    }

    public void setPicPro(Product picPro) {
        this.picPro = picPro;
    }

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo == null ? null : picInfo.trim();
    }

    //构造方法
	public Pic() {
		super();
	}

	public Pic(Product picPro, String picInfo) {
		super();
		this.picPro = picPro;
		this.picInfo = picInfo;
	}

	public Pic(Integer picId, Product picPro, String picInfo) {
		super();
		this.picId = picId;
		this.picPro = picPro;
		this.picInfo = picInfo;
	}

	@Override
	public String toString() {
		return "Pic [picId=" + picId + ", picProId=" + picPro + ", picInfo=" + picInfo + "]";
	}
	
	
    
    
}