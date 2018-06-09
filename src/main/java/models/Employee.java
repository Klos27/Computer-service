package models;

public class Employee {
	String firstname;
	String lastname;
	int id;
	int id_service_request;
	int orders;
	double earnings;
	
	public Employee(String firstname, String lastname, int id, int id_service_request) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.id_service_request = id_service_request;
	}
	
	public Employee(String firstname, String lastname, int id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	}
	
	public Employee(String firstname, String lastname, int id, int orders, double earnings) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.orders = orders;
		this.earnings = earnings;
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

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public double getEarnings() {
		return earnings;
	}

	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	@Override
	public String toString() {
		return "Employee [firstname=" + firstname + ", id=" + id + ", id_service_request=" + id_service_request + "]";
	}
}
