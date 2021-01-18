package net.codejava.javaee.bookstore.entity;


public class LotteryType {
	int id;
	String name;

	public LotteryType() {
	}

	public LotteryType(int id) {
		this.id = id;
	}

	public LotteryType(int id, String name) {
		this.id = id;
		this.name=name;
	}
	
	
	public LotteryType(String name) {
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
