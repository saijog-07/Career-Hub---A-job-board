package entity.model;

public class Company {

	private int companyID;
	private String companyName;
	private String location;
	
	
	public Company() {
		super();
	}

	public Company(int companyID, String companyName, String location) {
		super();
		this.companyID = companyID;
		this.companyName = companyName;
		this.location = location;
	}
	
	@Override
	public String toString() {
	    return "CompanyID: " + companyID +
	           ", Name: " + companyName +
	           ", Location: " + location;
	}


	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
	
}
