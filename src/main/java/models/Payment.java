package models;

import java.sql.Date;

public class Payment {
	private int id;
	private float amount;
	private int status;
	private Date creation_date;
	private int id_service_request;
	
	public Payment(int id, float amount, int status, Date creation_date, int id_service_request) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.creation_date = creation_date;
		this.id_service_request = id_service_request;
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
	public int getId_service_request() {
		return id_service_request;
	}
	public void setId_service_request(int id_service_request) {
		this.id_service_request = id_service_request;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", status=" + status + ", creation_date=" + creation_date
				+ ", id_service_request=" + id_service_request + "]";
	}
}
