package net.project.entity;


public class GroupM {
	int id;
	String name;

	public GroupM() {
	}

	public GroupM(int id) {
		this.id = id;
	}

	public GroupM(int id, String name) {
		this.id = id;
		this.name=name;
	}
	
	
	public GroupM(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
