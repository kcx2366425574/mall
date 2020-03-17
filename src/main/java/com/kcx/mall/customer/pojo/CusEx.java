package com.kcx.mall.customer.pojo;
/**
 * 普通用户扩展pojo类
 * @author kcx
 * @date 2020/3/8
 */
public class CusEx {
	
	//主键id
    private Integer ceId;

    //用户
    private Customer ceCus;

    //用户真实姓名
    private String ceCusName;

    //用户邮寄地址
    private String ceCusAddress;

    //用户身份证号
    private String ceCusIdcard;

    //-----------------getters and setters--------------
    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Customer getCeCus() {
        return ceCus;
    }

    public void setCeCusId(Customer ceCus) {
        this.ceCus = ceCus;
    }

    public String getCeCusName() {
        return ceCusName;
    }

    public void setCeCusName(String ceCusName) {
        this.ceCusName = ceCusName == null ? null : ceCusName.trim();
    }

    public String getCeCusAddress() {
        return ceCusAddress;
    }

    public void setCeCusAddress(String ceCusAddress) {
        this.ceCusAddress = ceCusAddress == null ? null : ceCusAddress.trim();
    }

    public String getCeCusIdcard() {
        return ceCusIdcard;
    }

    public void setCeCusIdcard(String ceCusIdcard) {
        this.ceCusIdcard = ceCusIdcard == null ? null : ceCusIdcard.trim();
    }

    //----------------构造方法--------------
	public CusEx() {
		super();
	}

	public CusEx(Customer ceCus, String ceCusName, String ceCusAddress, String ceCusIdcard) {
		super();
		this.ceCus = ceCus;
		this.ceCusName = ceCusName;
		this.ceCusAddress = ceCusAddress;
		this.ceCusIdcard = ceCusIdcard;
	}

	public CusEx(Integer ceId, Customer ceCus, String ceCusName, String ceCusAddress, String ceCusIdcard) {
		super();
		this.ceId = ceId;
		this.ceCus = ceCus;
		this.ceCusName = ceCusName;
		this.ceCusAddress = ceCusAddress;
		this.ceCusIdcard = ceCusIdcard;
	}

	@Override
	public String toString() {
		return "CusEx [ceId=" + ceId + ", ceCus=" + ceCus + ", ceCusName=" + ceCusName + ", ceCusAddress="
				+ ceCusAddress + ", ceCusIdcard=" + ceCusIdcard + "]";
	}
    
	
}