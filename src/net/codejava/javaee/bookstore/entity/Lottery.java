package net.codejava.javaee.bookstore.entity;


import java.sql.Date;

public class Lottery {
	int id;
	LotteryType type;
	Date dates;
	Prize prize;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

	public Lottery(int id, LotteryType type, Date dates, Prize prize) {
		super();
		this.id = id;
		this.type = type;
		this.dates = dates;
		this.prize = prize;
	}

	public Lottery(int id) {
		super();
		this.id = id;
	}

	public Lottery(LotteryType type, Date dates, Prize prize) {
		super();
		this.type = type;
		this.dates = dates;
		this.prize = prize;
	}
	

}
