package com.kcx.mall.product.pojo;

public class ProType {
    private Integer ptId;

    private String ptName;

    private String ptInfo;

    
    //getters and setters
    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName == null ? null : ptName.trim();
    }

    public String getPtInfo() {
        return ptInfo;
    }

    public void setPtInfo(String ptInfo) {
        this.ptInfo = ptInfo == null ? null : ptInfo.trim();
    }
    
    //------------构造方法
    public ProType() {
		super();
	}

	public ProType(String ptName, String ptInfo) {
		super();
		this.ptName = ptName;
		this.ptInfo = ptInfo;
	}

	public ProType(Integer ptId, String ptName, String ptInfo) {
		super();
		this.ptId = ptId;
		this.ptName = ptName;
		this.ptInfo = ptInfo;
	}

	@Override
	public String toString() {
		return "ProType [ptId=" + ptId + ", ptName=" + ptName + ", ptInfo=" + ptInfo + "]";
	}
    
    
}