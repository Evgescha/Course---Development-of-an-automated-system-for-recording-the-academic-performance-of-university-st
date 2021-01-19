package net.project.entity;


public class TeacherM {
	int id;
	String name;

	public TeacherM() {
	}

	public TeacherM(int id) {
		this.id = id;
	}

	public TeacherM(int id, String name) {
		this.id = id;
		this.name=name;
	}
	
	
	public TeacherM(String name) {
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

	@Override
	public String toString() {
		return  name;
	}


}
