package net.codejava.javaee.bookstore.entity;


public class Prize {
	int id;
	String name;
	String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Prize(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Prize(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Prize(int id) {
		super();
		this.id = id;
	}

}
