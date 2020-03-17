package com.kcx.mall.perm.pojo;

public class RolePerm {
    private Integer rpId;

    private Role role;

    private Permission perm;

	public RolePerm() {
		super();
	}

	public RolePerm(Integer rpId, Role role, Permission perm) {
		super();
		this.rpId = rpId;
		this.role = role;
		this.perm = perm;
	}

	public RolePerm(Role role, Permission perm) {
		super();
		this.role = role;
		this.perm = perm;
	}

	public Integer getRpId() {
		return rpId;
	}

	public void setRpId(Integer rpId) {
		this.rpId = rpId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPerm() {
		return perm;
	}

	public void setPerm(Permission perm) {
		this.perm = perm;
	}

	@Override
	public String toString() {
		return "RolePerm [rpId=" + rpId + ", role=" + role + ", perm=" + perm + "]";
	}

}