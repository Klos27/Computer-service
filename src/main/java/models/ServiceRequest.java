package models;

import java.sql.Date;

public class ServiceRequest {
	private int id;
	private int id_client;
	private String description;
	private int status;
	private Date start_date;
	private Date end_date;
	private int id_employee;
	private String firstname;
	private String lastname;
	
	public ServiceRequest(int id, int id_client, String description, int status, Date start_date, Date end_date) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.description = description;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public ServiceRequest(int id, int id_client, String description, int status, Date start_date, Date end_date, int id_employee) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.description = description;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
		this.id_employee = id_employee;
	}
	
	public ServiceRequest(int id, int id_client, String description, int status, Date start_date, Date end_date, int id_employee, String firstname, String lastname) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.description = description;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
		this.id_employee = id_employee;
		this.firstname = firstname;
		this.lastname = lastname;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getId_employee() {
		return id_employee;
	}

	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "ServiceRequest{" +
				"id=" + id +
				", id_client=" + id_client +
				", description='" + description + '\'' +
				", status=" + status +
				", start_date=" + start_date +
				", end_date=" + end_date +
				'}';
	}
}
