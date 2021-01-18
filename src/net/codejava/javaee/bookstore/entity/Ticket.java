package net.codejava.javaee.bookstore.entity;

public class Ticket {

	int id;
	Lottery lottery;
	String numbers;

	public Ticket(int id, Lottery lottery, String numbers) {
		super();
		this.id = id;
		this.lottery = lottery;
		this.numbers = numbers;
	}

	public Ticket(int id) {
		super();
		this.id = id;
	}

	public Ticket(Lottery lottery, String numbers) {
		super();
		this.lottery = lottery;
		this.numbers = numbers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

}
