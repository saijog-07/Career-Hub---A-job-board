package dao;

import entity.model.Applicant;
import entity.model.JobListing;

import java.util.List;

public interface IServiceProvider {
	
    void createProfile(String email, String firstName, String lastName, String phone, String resume);
    void apply(int applicantID, String coverLetter);
    void applyForJob(int jobID, int applicantID, String coverLetter);
    void postJob(int companyID, String jobTitle, String jobDescription, String jobLocation, double salary, String jobType);
    List<JobListing> getJobsBySalaryRange(double minSalary, double maxSalary);
    List<Applicant> getApplicants();
    List<JobListing> getJobs();
    
}

