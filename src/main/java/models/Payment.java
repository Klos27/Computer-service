package models;

import java.sql.Date;

public class Payment {
	private int id;
	private float amount;
	private int status;
	private Date creation_date;
	
	public Payment(int id, float amount, int status, Date creation_date) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.creation_date = creation_date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", status=" + status + ", creation_date=" + creation_date
				+ "]";
	}
}
