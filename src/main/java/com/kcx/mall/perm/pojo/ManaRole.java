package com.kcx.mall.perm.pojo;

import com.kcx.mall.manager.pojo.Manager;

public class ManaRole {
    private Integer mrId;

    private Manager man;

    private Role role;

	public ManaRole() {
		super();
	}

	public ManaRole(Integer mrId, Manager man, Role role) {
		super();
		this.mrId = mrId;
		this.man = man;
		this.role = role;
	}
	
	public ManaRole(Manager man, Role role) {
		super();
		this.man = man;
		this.role = role;
	}

	public Integer getmrId() {
		return mrId;
	}

	public void setmrId(Integer mrId) {
		this.mrId = mrId;
	}

	public Manager getEmp() {
		return man;
	}

	public void setEmp(Manager man) {
		this.man = man;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ManaRole [mrId=" + mrId + ", man=" + man + ", role=" + role + "]";
	}

}