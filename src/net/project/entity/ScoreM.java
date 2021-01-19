package net.project.entity;

public class ScoreM {

	int id;
	StudentM student;
	SubjectM subject;
	int score;
	int semestr;

	public ScoreM(int id, StudentM student, SubjectM subject, int score, int semestr) {
		super();
		this.id = id;
		this.student = student;
		this.subject = subject;
		this.score = score;
		this.semestr = semestr;
	}

	public ScoreM(StudentM student, SubjectM subject, int score, int semestr) {
		super();
		this.student = student;
		this.subject = subject;
		this.score = score;
		this.semestr = semestr;
	}

	public ScoreM(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentM getStudent() {
		return student;
	}

	public void setStudent(StudentM student) {
		this.student = student;
	}

	public SubjectM getSubject() {
		return subject;
	}

	public void setSubject(SubjectM subject) {
		this.subject = subject;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSemestr() {
		return semestr;
	}

	public void setSemestr(int semestr) {
		this.semestr = semestr;
	}

}
