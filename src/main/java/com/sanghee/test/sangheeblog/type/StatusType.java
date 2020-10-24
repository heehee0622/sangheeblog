package com.sanghee.test.sangheeblog.type;

public enum StatusType {
	SHOW("Y"), HIDE("N"),;

	private String name;
	private StatusType(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
