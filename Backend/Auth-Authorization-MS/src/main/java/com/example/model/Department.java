package com.example.model;

public class Department {
	
	private int deptid;
	private String dname;
	private int locid;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int deptid, String dname, int locid) {
		super();
		this.deptid = deptid;
		this.dname = dname;
		this.locid = locid;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getLocid() {
		return locid;
	}

	public void setLocid(int locid) {
		this.locid = locid;
	}
	
	
	
}
