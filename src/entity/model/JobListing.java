package entity.model;
import java.time.LocalDateTime;


public class JobListing {

	private int jobID;
	private int companyID;
	private String jobTitle;
	private String jobDescription;
	private String jobLocation;
	private double salary;
	private String jobType;
	private LocalDateTime postedDate;
	
	
	public JobListing() {
		super();
	}

	public JobListing(int jobID, int companyID, String jobTitle, String jobDescription, String jobLocation,
			double salary, String jobType, LocalDateTime postedDate) {
		super();
		this.jobID = jobID;
		this.companyID = companyID;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.jobLocation = jobLocation;
		this.salary = salary;
		this.jobType = jobType;
		this.postedDate = postedDate;
	}
	
	@Override
	public String toString() {
	    return "JobID: " + jobID +
	           ", Title: " + jobTitle +
	           ", CompanyID: " + companyID +
	           ", Location: " + jobLocation +
	           ", Salary: " + salary +
	           ", Type: " + jobType +
	           ", Posted: " + postedDate;
	}


	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public LocalDateTime getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDateTime postedDate) {
		this.postedDate = postedDate;
	}
	
	
	
	
	
}

