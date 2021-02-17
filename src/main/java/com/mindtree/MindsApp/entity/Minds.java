package com.mindtree.MindsApp.entity;

public class Minds {
	private short id;
	private String name;
	private String phoneno;
	private String role;
	
	public Minds() {
		// TODO Auto-generated constructor stub
	}

	public Minds(short id, String name, String phoneno, String role) {
		super();
		this.id = id;
		this.name = name;
		this.phoneno = phoneno;
		this.role = role;
	}

	public short getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public String getRole() {
		return role;
	}

	public void setId(short id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return String.format("Minds [id=%s, name=%s, phoneno=%s, role=%s]", id, name, phoneno, role);
	}
	
	
}
