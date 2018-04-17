package models;

import java.sql.Date;

public class ServiceRequest {
	private int id;
	private int id_client;
	private String decription;
	private int status;
	private Date start_date;
	private Date end_date;
	public ServiceRequest(int id, int id_client, String decription, int status, Date start_date, Date end_date) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.decription = decription;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
}
