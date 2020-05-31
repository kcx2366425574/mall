package com.kcx.mall.product.pojo;

import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.manager.pojo.Manager;
import com.kcx.mall.shop.pojo.Shop;

public class Product {
    private Integer proId;

    private String proName;

    private Float proPrice;

    private String proInfo;

    private Customer proCus;

    private Shop proShop;

    private ProType proPt;

    private String proState;

    private Manager proMana;

    private String proPic;

    //--------------getters and setters----
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

    public ProType getProPt() {
        return proPt;
    }

    public void setProPt(ProType proPt) {
        this.proPt = proPt;
    }

    public String getProState() {
        return proState;
    }

    public void setProState(String proState) {
        this.proState = proState == null ? null : proState.trim();
    }

    public Manager getProMana() {
        return proMana;
    }

    public void setProMana(Manager proMana) {
        this.proMana = proMana;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic == null ? null : proPic.trim();
    }

    //---------构造方法------------
	public Product() {
		super();
	}

	public Product(String proName, Float proPrice, String proInfo, Customer proCus, Shop proShop, ProType proPt,
			String proState, Manager proMana, String proPic) {
		super();
		this.proName = proName;
		this.proPrice = proPrice;
		this.proInfo = proInfo;
		this.proCus = proCus;
		this.proShop = proShop;
		this.proPt = proPt;
		this.proState = proState;
		this.proMana = proMana;
		this.proPic = proPic;
	}
	
	

	public Product(String proName, Float proPrice, String proInfo, ProType proPt, String proState, Manager proMana,
			String proPic) {
		super();
		this.proName = proName;
		this.proPrice = proPrice;
		this.proInfo = proInfo;
		this.proPt = proPt;
		this.proState = proState;
		this.proMana = proMana;
		this.proPic = proPic;
	}
	
	
	//前端专用构造方法
	public Product(String proName, Float proPrice, String proInfo, ProType proPt, String proPic) {
		super();
		this.proName = proName;
		this.proPrice = proPrice;
		this.proInfo = proInfo;
		this.proPt = proPt;
		this.proPic = proPic;
	}

	public Product(Integer proId, String proName, Float proPrice, String proInfo, Customer proCus, Shop proShop,
			ProType proPt, String proState, Manager proMana, String proPic) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proInfo = proInfo;
		this.proCus = proCus;
		this.proShop = proShop;
		this.proPt = proPt;
		this.proState = proState;
		this.proMana = proMana;
		this.proPic = proPic;
	}

	@Override
	public String toString() {
		return "Product [proId=" + proId + ", proName=" + proName + ", proPrice=" + proPrice + ", proInfo=" + proInfo
				+ ", proCus=" + proCus + ", proShop=" + proShop + ", proPt=" + proPt + ", proState=" + proState
				+ ", proMana=" + proMana + ", proPic=" + proPic + "]";
	}
    
	
    
}