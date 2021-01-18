package net.project.entity;

import java.sql.Date;

public class StudentM {
	int id;
	String name;
	GroupM group;

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

	public GroupM getGroup() {
		return group;
	}

	public void setGroup(GroupM group) {
		this.group = group;
	}

	public StudentM() {
		super();
	}

	public StudentM(String name, GroupM group) {
		super();
		this.name = name;
		this.group = group;
	}

	public StudentM(int id, String name, GroupM group) {
		super();
		this.id = id;
		this.name = name;
		this.group = group;
	}

}
