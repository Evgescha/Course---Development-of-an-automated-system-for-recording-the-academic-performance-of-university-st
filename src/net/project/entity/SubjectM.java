package net.project.entity;

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

	public TeacherM getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherM teacher) {
		this.teacher = teacher;
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

	public SubjectM(int id) {
		super();
		this.id = id;
	}

}
