package models;

import java.sql.Date;

public class Message {

	private int id;
	private int id_user;
	private String first_name;
	private String last_name;
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	private int id_service_request;
	private Date date;
	private String content;
	
	public Message(int id, int id_user, String first_name, String last_name, int id_service_request, Date date, String content) {
		this.id = id;
		this.id_user = id_user;
		this.first_name = first_name;
		this.last_name = last_name;
		this.id_service_request = id_service_request;
		this.date = date;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_service_request() {
		return id_service_request;
	}

	public void setId_service_request(int id_service_request) {
		this.id_service_request = id_service_request;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}