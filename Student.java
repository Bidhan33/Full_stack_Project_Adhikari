




package model;

/**
 * Entity class: Movie
 * 
 * @author Kari
 * @version 1.1 3.11.2019
 */
public class Student {
	private int id;
	private String firstname;
	private String lastname;
	private String streetaddress;
	private String postoffice;
	private int postcode;

	public Student() {
		id = -1;
		firstname = "";
		lastname = "";
		streetaddress = "";
		postoffice = "";
	     postcode = -1;
	}

	public Student(int id, String firstname, String lastname, String streetaddress, String postoffice, int postcode) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.postoffice = postoffice;
		this.postcode = postcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	public String getStreetAddress() {
		return streetaddress;
	}
	public void setStreetAddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getPostOffice() {
		return postoffice;
	}

	public void setPostOffice(String postoffice) {
		this.postoffice = postoffice;
	}


	public int getPostCode() {
		return postcode;
	}

	public void setPostCode(int postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return id + ": " + firstname + lastname + ", " + streetaddress + ", " + postcode + " " + postoffice;
	}
}
// End
