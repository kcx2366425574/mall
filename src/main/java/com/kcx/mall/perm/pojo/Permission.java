package com.kcx.mall.perm.pojo;

public class Permission {
    private Integer permId;

    private String permName;

    private String permInfo;

	public Permission() {
		super();
	}

	public Permission(Integer permId, String permName, String permInfo) {
		super();
		this.permId = permId;
		this.permName = permName;
		this.permInfo = permInfo;
	}

	public Permission(String permName, String permInfo) {
		super();
		this.permName = permName;
		this.permInfo = permInfo;
	}

	public Integer getPermId() {
		return permId;
	}

	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public String getPermInfo() {
		return permInfo;
	}

	public void setPermInfo(String permInfo) {
		this.permInfo = permInfo;
	}

	@Override
	public String toString() {
		return "Permission [permId=" + permId + ", permName=" + permName + ", permInfo=" + permInfo + "]";
	}

}