package com.kcx.mall.product.pojo;

import java.util.Date;

import com.kcx.mall.customer.pojo.Customer;
import com.kcx.mall.shop.pojo.Shop;

public class ProRecord {
    private Integer prId;

    private Product prPro;

    private Customer prCus;

    private Shop prShop;

    private Date prTime;

    //getters and setters
    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    public Product getPrPro() {
        return prPro;
    }

    public void setPrPro(Product prPro) {
        this.prPro = prPro;
    }

    public Customer getPrCus() {
        return prCus;
    }

    public void setPrCus(Customer prCus) {
        this.prCus = prCus;
    }

    public Shop getPrShop() {
        return prShop;
    }

    public void setPrShop(Shop prShop) {
        this.prShop = prShop;
    }

    public Date getPrTime() {
        return prTime;
    }

    public void setPrTime(Date prTime) {
        this.prTime = prTime;
    }

    //构造方法
	public ProRecord() {
		super();
	}

	public ProRecord(Product prPro, Shop prShop, Date prTime) {
		super();
		this.prPro = prPro;
		this.prShop = prShop;
		this.prTime = prTime;
	}

	public ProRecord(Integer prId, Product prPro, Customer prCus, Date prTime) {
		super();
		this.prId = prId;
		this.prPro = prPro;
		this.prCus = prCus;
		this.prTime = prTime;
	}

	public ProRecord(Integer prId, Product prPro, Customer prCus, Shop prShop, Date prTime) {
		super();
		this.prId = prId;
		this.prPro = prPro;
		this.prCus = prCus;
		this.prShop = prShop;
		this.prTime = prTime;
	}

	@Override
	public String toString() {
		return "ProRecord [prId=" + prId + ", prPro=" + prPro + ", prCus=" + prCus + ", prShop=" + prShop + ", prTime="
				+ prTime + "]";
	}
    
    
}