package com.kcx.mall.customer.pojo;
/**
 * 普通用户pojo类
 * @author kcx
 * @date 2020/3/8
 */
public class Customer {
	
	//主键id
    private Integer cusId;
    
    //用户登录名
    private String cusLoginName;

    //用户登录密码
    private String cusPassword;

    //显示用户登录状态，是否在线
    private Boolean cusIson;

    //用户余额
    private Float cusAccount;

    //用户其他信息（联系方式）
    private String cusContanctInfo;
    
    //用户头像
    private String cusPhoto;

    //------------getters and setters
    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusLoginName() {
        return cusLoginName;
    }

    public void setCusLoginName(String cusLoginName) {
        this.cusLoginName = cusLoginName == null ? null : cusLoginName.trim();
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public void setCusPassword(String cusPassword) {
        this.cusPassword = cusPassword == null ? null : cusPassword.trim();
    }

    public Boolean getCusIson() {
        return cusIson;
    }

    public void setCusIson(Boolean cusIson) {
        this.cusIson = cusIson;
    }

    public Float getCusAccount() {
        return cusAccount;
    }

    public void setCusAccount(Float cusAccount) {
        this.cusAccount = cusAccount;
    }
    
    

    public String getCusPhoto() {
		return cusPhoto;
	}

	public void setCusPhoto(String cusPhoto) {
		this.cusPhoto = cusPhoto;
	}

	public String getCusContanctInfo() {
        return cusContanctInfo;
    }

    public void setCusContanctInfo(String cusContanctInfo) {
        this.cusContanctInfo = cusContanctInfo == null ? null : cusContanctInfo.trim();
    }

    
    //-------------构造方法
	public Customer() {
		super();
	}

	public Customer(String cusLoginName, String cusPassword, Boolean cusIson, Float cusAccount,
			String cusContanctInfo) {
		super();
		this.cusLoginName = cusLoginName;
		this.cusPassword = cusPassword;
		this.cusIson = cusIson;
		this.cusAccount = cusAccount;
		this.cusContanctInfo = cusContanctInfo;
	}
	

	public Customer(String cusLoginName, String cusPassword, Boolean cusIson, Float cusAccount, String cusContanctInfo,
			String cusPhoto) {
		super();
		this.cusLoginName = cusLoginName;
		this.cusPassword = cusPassword;
		this.cusIson = cusIson;
		this.cusAccount = cusAccount;
		this.cusContanctInfo = cusContanctInfo;
		this.cusPhoto = cusPhoto;
	}

	public Customer(String cusLoginName, String cusPassword, Boolean cusIson, Float cusAccount) {
		super();
		this.cusLoginName = cusLoginName;
		this.cusPassword = cusPassword;
		this.cusIson = cusIson;
		this.cusAccount = cusAccount;
	}

	public Customer(Integer cusId, String cusLoginName, String cusPassword, Boolean cusIson, Float cusAccount,
			String cusContanctInfo) {
		super();
		this.cusId = cusId;
		this.cusLoginName = cusLoginName;
		this.cusPassword = cusPassword;
		this.cusIson = cusIson;
		this.cusAccount = cusAccount;
		this.cusContanctInfo = cusContanctInfo;
	}

	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusLoginName=" + cusLoginName + ", cusPassword=" + cusPassword
				+ ", cusIson=" + cusIson + ", cusAccount=" + cusAccount + ", cusContanctInfo=" + cusContanctInfo + "]";
	}
    
    
}