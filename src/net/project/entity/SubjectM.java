package net.project.entity;

import java.sql.Date;

public class SubjectM {
	int id;
	String name;
	TeacherM teacher;

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

	public SubjectM() {
		super();
	}

	public SubjectM(int id, String name, TeacherM teacher) {
		super();
		this.id = id;
		this.name = name;
		this.teacher = teacher;
	}

	public SubjectM(String name, TeacherM teacher) {
		super();
		this.name = name;
		this.teacher = teacher;
	}

}
