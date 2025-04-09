package dao;

import entity.model.Applicant;
import entity.model.JobApplication;
import entity.model.JobListing;
import exception.InvalidEmailFormatException;
import exception.NegativeSalaryException;
import manager.DatabaseManager;

import java.time.LocalDateTime;
import java.util.List;

public class ServiceProviderImpl implements IServiceProvider {

    private final DatabaseManager db;

    public ServiceProviderImpl() {
        this.db = new DatabaseManager();
    }

    @Override
    public void createProfile(String email, String firstName, String lastName, String phone, String resume) {
        try {
            if (!email.matches("^.+@.+\\..+$")) {
                throw new InvalidEmailFormatException("Invalid email format: " + email);
            }
            Applicant applicant = new Applicant(0, firstName, lastName, email, phone, resume);
            db.insertApplicant(applicant);
        } catch (InvalidEmailFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void apply(int applicantID, String coverLetter) {
        int jobID = getLatestJobID();
        if (jobID == -1) {
            System.out.println("No job available to apply to.");
            return;
        }
        applyForJob(jobID, applicantID, coverLetter);
    }

    @Override
    public void applyForJob(int jobID, int applicantID, String coverLetter) {
        JobApplication application = new JobApplication(0, jobID, applicantID, LocalDateTime.now(), coverLetter);
        db.insertJobApplication(application);
    }

    @Override
    public void postJob(int companyID, String jobTitle, String jobDescription, String jobLocation, double salary, String jobType) {
        try {
            if (salary < 0) {
                throw new NegativeSalaryException("Salary cannot be negative: " + salary);
            }
            JobListing job = new JobListing(0, companyID, jobTitle, jobDescription, jobLocation, salary, jobType, LocalDateTime.now());
            db.insertJobListing(job);
        } catch (NegativeSalaryException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Applicant> getApplicants() {
        return db.getApplicants();
    }

    @Override
    public List<JobListing> getJobs() {
        return db.getJobListings();
    }
    
    @Override
    public List<JobListing> getJobsBySalaryRange(double minSalary, double maxSalary) {
        return db.getJobListingsBySalaryRange(minSalary, maxSalary);
    }


    private int getLatestJobID() {
        List<JobListing> jobs = db.getJobListings();
        if (jobs.isEmpty()) return -1;
        return jobs.get(jobs.size() - 1).getJobID();
    }
}