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
    
    //管理员头像
    private String manaPhoto;
    
    //管理员说明
    private String manaInfo;

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
    
    
    
    public String getManaInfo() {
		return manaInfo;
	}

	public void setManaInfo(String manaInfo) {
		this.manaInfo = manaInfo;
	}

	public String getManaPhoto() {
		return manaPhoto;
	}

	public void setManaPhoto(String manaPhoto) {
		this.manaPhoto = manaPhoto;
	}
    

    //--------------------------构造方法

	public Manager() {
		super();
	}

    
	public Manager(String manaName, String manaPassword, Boolean manaIson,String manaInfo) {
		super();
		this.manaName = manaName;
		this.manaPassword = manaPassword;
		this.manaIson = manaIson;
		this.manaInfo = manaInfo;
	}
	

	public Manager(String manaName, String manaPassword, Boolean manaIson, String manaPhoto,String manaInfo) {
		super();
		this.manaName = manaName;
		this.manaPassword = manaPassword;
		this.manaIson = manaIson;
		this.manaPhoto = manaPhoto;
		this.manaInfo = manaInfo;
	}

	public Manager(Integer manaId, String manaName, String manaPassword, Boolean manaIson, String manaPhoto,String manaInfo) {
		super();
		this.manaId = manaId;
		this.manaName = manaName;
		this.manaPassword = manaPassword;
		this.manaIson = manaIson;
		this.manaPhoto = manaPhoto;
		this.manaInfo = manaInfo;
	}

	public Manager(Integer manaId, String manaName, String manaPassword, Boolean manaIson, String manaInfo) {
		super();
		this.manaId = manaId;
		this.manaName = manaName;
		this.manaPassword = manaPassword;
		this.manaIson = manaIson;
		this.manaInfo = manaInfo;
	}

	@Override
	public String toString() {
		return "Manager [manaId=" + manaId + ", manaName=" + manaName + ", manaPassword=" + manaPassword + ", manaIson="
				+ manaIson + ", manaPhoto=" + manaPhoto + ", manaInfo=" + manaInfo + "]";
	}
    
    
}