package com.kcx.mall.manager.pojo;
/**
 * @date 2020-03-08
 * @author kcx
 * @description 管理员pojo类
 */
public class Manager {
	
	//主键id
    private Integer manaId;

    //管理员登录名
    private String manaName;

    //管理员登录密码
    private String manaPassword;

    //管理员登录状态，是否在线
    private Boolean manaIson;

    //-----------------------getters and setters
    public Integer getManaId() {
        return manaId;
    }

    public void setManaId(Integer manaId) {
        this.manaId = manaId;
    }

    public String getManaName() {
        return manaName;
    }

    public void setManaName(String manaName) {
        this.manaName = manaName == null ? null : manaName.trim();
    }

    public String getManaPassword() {
        return manaPassword;
    }

    public void setManaPassword(String manaPassword) {
        this.manaPassword = manaPassword == null ? null : manaPassword.trim();
    }

    public Boolean getManaIson() {
        return manaIson;
    }

    public void setManaIson(Boolean manaIson) {
        this.manaIson = manaIson;
    }

    //--------------------------构造方法

	public Manager() {
		super();
	}

    
	public Manager(String manaName, String manaPassword, Boolean manaIson) {
		super();
		this.manaName = manaName;
		this.manaPassword = manaPassword;
		this.manaIson = manaIson;
	}

	public Manager(Integer manaId, String manaName, String manaPassword, Boolean manaIson) {
		super();
		this.manaId = manaId;
		this.manaName = manaName;
		this.manaPassword = manaPassword;
		this.manaIson = manaIson;
	}

	@Override
	public String toString() {
		return "Manager [manaId=" + manaId + ", manaName=" + manaName + ", manaPassword=" + manaPassword + ", manaIson="
				+ manaIson + "]";
	}
    
    
}