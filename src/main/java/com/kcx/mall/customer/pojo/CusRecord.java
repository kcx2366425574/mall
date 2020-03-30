package com.kcx.mall.customer.pojo;

import java.util.Date;

public class CusRecord {
    private Integer crId;

    private Customer crCus;

    private Float crCusRecharge;

    private Float crCusRest;

    private Date crTime;

    
    //---------------getters and setters
    public Integer getCrId() {
        return crId;
    }

    public void setCrId(Integer crId) {
        this.crId = crId;
    }

    public Customer getCrCus() {
        return crCus;
    }

    public void setCrCus(Customer crCus) {
        this.crCus = crCus;
    }

    public Float getCrCusRecharge() {
        return crCusRecharge;
    }

    public void setCrCusRecharge(Float crCusRecharge) {
        this.crCusRecharge = crCusRecharge;
    }

    public Float getCrCusRest() {
        return crCusRest;
    }

    public void setCrCusRest(Float crCusRest) {
        this.crCusRest = crCusRest;
    }

    public Date getCrTime() {
        return crTime;
    }

    public void setCrTime(Date crTime) {
        this.crTime = crTime;
    }

    //构造方法
	public CusRecord() {
		super();
	}

	public CusRecord(Customer crCus, Float crCusRecharge, Float crCusRest, Date crTime) {
		super();
		this.crCus = crCus;
		this.crCusRecharge = crCusRecharge;
		this.crCusRest = crCusRest;
		this.crTime = crTime;
	}

	public CusRecord(Integer crId, Customer crCus, Float crCusRecharge, Float crCusRest, Date crTime) {
		super();
		this.crId = crId;
		this.crCus = crCus;
		this.crCusRecharge = crCusRecharge;
		this.crCusRest = crCusRest;
		this.crTime = crTime;
	}

	@Override
	public String toString() {
		return "CusRecord [crId=" + crId + ", crCus=" + crCus + ", crCusRecharge=" + crCusRecharge + ", crCusRest="
				+ crCusRest + ", crTime=" + crTime + "]";
	}
    
	
   
    
    
}