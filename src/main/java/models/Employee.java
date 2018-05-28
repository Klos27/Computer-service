package models;

public class Employee {
	String firstname;
	int id;
	int id_service_request;
	
	public Employee(String firstname, int id, int id_service_request) {
		this.firstname = firstname;
		this.id = id;
		this.id_service_request = id_service_request;
	}
	
	public Employee(String firstname, int id) {
		this.firstname = firstname;
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_service_request() {
		return id_service_request;
	}

	public void setId_service_request(int id_service_request) {
		this.id_service_request = id_service_request;
	}

	@Override
	public String toString() {
		return "Employee [firstname=" + firstname + ", id=" + id + ", id_service_request=" + id_service_request + "]";
	}
}
