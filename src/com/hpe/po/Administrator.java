package com.hpe.po;

public class Administrator {
	private String adminNUmber;
	private String adminPwd;
	private String adminName;
	
	public String getAdminNUmber() {
		return adminNUmber;
	}
	public void setAdminNUmber(String adminNUmber) {
		this.adminNUmber = adminNUmber;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	@Override
	public String toString() {
		return "Administrator [adminNUmber=" + adminNUmber + ", adminPwd=" + adminPwd + ", adminName=" + adminName
				+ "]";
	}
	
}
