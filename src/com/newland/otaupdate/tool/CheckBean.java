package com.newland.otaupdate.tool;

public class CheckBean{
	String firmName;
	String firmMark;
	boolean isChecked;
	public CheckBean(String name,String mark){
		this.firmName = name;
		this.firmMark = mark;
		this.isChecked=false;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getFirmMark() {
		return firmMark;
	}
	public void setFirmMark(String firmMark) {
		this.firmMark = firmMark;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}
