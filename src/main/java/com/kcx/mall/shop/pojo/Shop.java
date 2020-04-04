package com.kcx.mall.shop.pojo;

import com.kcx.mall.manager.pojo.Manager;

public class Shop {
	//主键id
    private Integer shopId;
    //商店名
    private String shopName;
    //商店登录名
    private String shopMinname;
    //商店登录密码
    private String shopPassword;

    //商店其他信息
    private String shopInfo;
    //商家地址
    private String shopAddress;
    //商家经营者
    private String shopOwner;
    //经营者身份证号
    private String shopOwnerIdcard;
    //商家余额
    private Float shopAccount;
    //商家审核状态
    private String shopState;
    //商家入驻审核管理员id
    private Manager shopMana;
    
    //商家图标
    private String shopPhoto;

    //----------------getters and setters------------------
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }
    
    

    public String getShopPhoto() {
		return shopPhoto;
	}

	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

	public String getShopMinname() {
        return shopMinname;
    }

    public void setShopMinname(String shopMinname) {
        this.shopMinname = shopMinname == null ? null : shopMinname.trim();
    }

    public String getShopPassword() {
        return shopPassword;
    }

    public void setShopPassword(String shopPassword) {
        this.shopPassword = shopPassword == null ? null : shopPassword.trim();
    }

    public String getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(String shopInfo) {
        this.shopInfo = shopInfo == null ? null : shopInfo.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner == null ? null : shopOwner.trim();
    }

    public String getShopOwnerIdcard() {
        return shopOwnerIdcard;
    }

    public void setShopOwnerIdcard(String shopOwnerIdcard) {
        this.shopOwnerIdcard = shopOwnerIdcard == null ? null : shopOwnerIdcard.trim();
    }

    public Float getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(Float shopAccount) {
        this.shopAccount = shopAccount;
    }

    public String getShopState() {
        return shopState;
    }

    public void setShopState(String shopState) {
        this.shopState = shopState == null ? null : shopState.trim();
    }

    public Manager getShopMana() {
        return shopMana;
    }

    public void setShopMana(Manager shopMana) {
        this.shopMana = shopMana;
    }

    //-------------构造方法--------------
	public Shop() {
		super();
	}

	//没有id，没有入驻审核管理员id
	public Shop(String shopName, String shopMinname, String shopPassword, String shopInfo, String shopAddress,
			String shopOwner, String shopOwnerIdcard, Float shopAccount, String shopState) {
		super();
		this.shopName = shopName;
		this.shopMinname = shopMinname;
		this.shopPassword = shopPassword;
		this.shopInfo = shopInfo;
		this.shopAddress = shopAddress;
		this.shopOwner = shopOwner;
		this.shopOwnerIdcard = shopOwnerIdcard;
		this.shopAccount = shopAccount;
		this.shopState = shopState;
	}

	//只没有主键id
	public Shop(String shopName, String shopMinname, String shopPassword, String shopInfo, String shopAddress,
			String shopOwner, String shopOwnerIdcard, Float shopAccount, String shopState, Manager shopMana) {
		super();
		this.shopName = shopName;
		this.shopMinname = shopMinname;
		this.shopPassword = shopPassword;
		this.shopInfo = shopInfo;
		this.shopAddress = shopAddress;
		this.shopOwner = shopOwner;
		this.shopOwnerIdcard = shopOwnerIdcard;
		this.shopAccount = shopAccount;
		this.shopState = shopState;
		this.shopMana = shopMana;
	}
	
	

	public Shop(String shopName, String shopMinname, String shopPassword, String shopInfo, String shopAddress,
			String shopOwner, String shopOwnerIdcard, Float shopAccount, String shopState, Manager shopMana,
			String shopPhoto) {
		super();
		this.shopName = shopName;
		this.shopMinname = shopMinname;
		this.shopPassword = shopPassword;
		this.shopInfo = shopInfo;
		this.shopAddress = shopAddress;
		this.shopOwner = shopOwner;
		this.shopOwnerIdcard = shopOwnerIdcard;
		this.shopAccount = shopAccount;
		this.shopState = shopState;
		this.shopMana = shopMana;
		this.shopPhoto = shopPhoto;
	}

	public Shop(Integer shopId, String shopName, String shopMinname, String shopPassword, String shopInfo,
			String shopAddress, String shopOwner, String shopOwnerIdcard, Float shopAccount, String shopState,
			Manager shopMana) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopMinname = shopMinname;
		this.shopPassword = shopPassword;
		this.shopInfo = shopInfo;
		this.shopAddress = shopAddress;
		this.shopOwner = shopOwner;
		this.shopOwnerIdcard = shopOwnerIdcard;
		this.shopAccount = shopAccount;
		this.shopState = shopState;
		this.shopMana = shopMana;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopMinname=" + shopMinname + ", shopPassword="
				+ shopPassword + ", shopInfo=" + shopInfo + ", shopAddress=" + shopAddress + ", shopOwner=" + shopOwner
				+ ", shopOwnerIdcard=" + shopOwnerIdcard + ", shopAccount=" + shopAccount + ", shopState=" + shopState
				+ ", shopMana=" + shopMana + "]";
	}
	
	
    
}