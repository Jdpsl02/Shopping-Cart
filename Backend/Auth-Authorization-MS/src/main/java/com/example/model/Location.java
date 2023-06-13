package com.example.model;

public class Location {
	private int locid;
	private String locname;
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(int locid, String locname) {
		super();
		this.locid = locid;
		this.locname = locname;
	}
	public int getLocid() {
		return locid;
	}
	public void setLocid(int locid) {
		this.locid = locid;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
	
	
}
