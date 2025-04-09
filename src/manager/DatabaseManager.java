package manager;

import entity.model.*;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.DatabaseConnectionException;

public class DatabaseManager {

    public void initializeDatabase() {
        try {
			try (Connection conn = DBConnUtil.getConnection()) {
			    Statement stmt = conn.createStatement();

			    String companyTable = "CREATE TABLE IF NOT EXISTS Companies (" +
			            "CompanyID INT PRIMARY KEY," +
			            "CompanyName VARCHAR(100)," +
			            "Location VARCHAR(100))";

			    String applicantTable = "CREATE TABLE IF NOT EXISTS Applicants (" +
			            "ApplicantID INT PRIMARY KEY," +
			            "FirstName VARCHAR(100)," +
			            "LastName VARCHAR(100)," +
			            "Email VARCHAR(100)," +
			            "Phone VARCHAR(20)," +
			            "Resume TEXT)";

			    String jobTable = "CREATE TABLE IF NOT EXISTS Jobs (" +
			            "JobID INT PRIMARY KEY," +
			            "CompanyID INT," +
			            "JobTitle VARCHAR(100)," +
			            "JobDescription TEXT," +
			            "JobLocation VARCHAR(100)," +
			            "Salary DECIMAL(10,2)," +
			            "JobType VARCHAR(50)," +
			            "PostedDate DATETIME," +
			            "FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID))";

			    String appTable = "CREATE TABLE IF NOT EXISTS Applications (" +
			            "ApplicationID INT PRIMARY KEY," +
			            "JobID INT," +
			            "ApplicantID INT," +
			            "ApplicationDate DATETIME," +
			            "CoverLetter TEXT," +
			            "FOREIGN KEY (JobID) REFERENCES Jobs(JobID)," +
			            "FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID))";

			    stmt.execute(companyTable);
			    stmt.execute(applicantTable);
			    stmt.execute(jobTable);
			    stmt.execute(appTable);

			    System.out.println("Database schema initialized.");
			} catch (DatabaseConnectionException e) {
			    System.err.println("Database connection error while inserting job listing: " + e.getMessage());
			} catch (Exception e) {
			    e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void insertCompany(Company company) {
        String sql = "INSERT INTO Companies VALUES (?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, company.getCompanyID());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getLocation());
            ps.executeUpdate();

            System.out.println("Company inserted.");
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertApplicant(Applicant applicant) {
        String sql = "INSERT INTO Applicants VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, applicant.getApplicantID());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getLastName());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getPhone());
            ps.setString(6, applicant.getResume());

            ps.executeUpdate();
            System.out.println("Applicant inserted.");
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertJobListing(JobListing job) {
        String sql = "INSERT INTO Jobs VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, job.getJobID());
            ps.setInt(2, job.getCompanyID());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6, job.getSalary());
            ps.setString(7, job.getJobType());
            ps.setTimestamp(8, Timestamp.valueOf(job.getPostedDate()));

            ps.executeUpdate();
            System.out.println("Job listing inserted.");
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertJobApplication(JobApplication app) {
        String sql = "INSERT INTO Applications VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, app.getApplicationID());
            ps.setInt(2, app.getJobID());
            ps.setInt(3, app.getApplicantID());
            ps.setTimestamp(4, Timestamp.valueOf(app.getApplicationDate()));
            ps.setString(5, app.getCoverLetter());

            ps.executeUpdate();
            System.out.println("Job application submitted.");
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<JobListing> getJobListings() {
        List<JobListing> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Jobs";
        try {
			try (Connection conn = DBConnUtil.getConnection();
			     Statement stmt = conn.createStatement();
			     ResultSet rs = stmt.executeQuery(sql)) {

			    while (rs.next()) {
			        JobListing job = new JobListing(
			                rs.getInt("JobID"),
			                rs.getInt("CompanyID"),
			                rs.getString("JobTitle"),
			                rs.getString("JobDescription"),
			                rs.getString("JobLocation"),
			                rs.getDouble("Salary"),
			                rs.getString("JobType"),
			                rs.getTimestamp("PostedDate").toLocalDateTime()
			        );
			        jobs.add(job);
			    }
			} catch (DatabaseConnectionException e) {
			    System.err.println("Database connection error while inserting job listing: " + e.getMessage());
			} catch (Exception e) {
			    e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jobs;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Companies";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Company c = new Company(
                        rs.getInt("CompanyID"),
                        rs.getString("CompanyName"),
                        rs.getString("Location")
                );
                companies.add(c);
            }
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }

    public List<Applicant> getApplicants() {
        List<Applicant> list = new ArrayList<>();
        String sql = "SELECT * FROM Applicants";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Applicant a = new Applicant(
                        rs.getInt("ApplicantID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Resume")
                );
                list.add(a);
            }
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> apps = new ArrayList<>();
        String sql = "SELECT * FROM Applications WHERE JobID = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jobID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JobApplication app = new JobApplication(
                        rs.getInt("ApplicationID"),
                        rs.getInt("JobID"),
                        rs.getInt("ApplicantID"),
                        rs.getTimestamp("ApplicationDate").toLocalDateTime(),
                        rs.getString("CoverLetter")
                );
                apps.add(app);
            }
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;
    }
    
    public List<JobListing> getJobListingsBySalaryRange(double minSalary, double maxSalary) {
        List<JobListing> jobs = new ArrayList<>();

        String sql = "SELECT * FROM Jobs WHERE salary BETWEEN ? AND ?";
        try (Connection conn = DBConnUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, minSalary);
            stmt.setDouble(2, maxSalary);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JobListing job = new JobListing(
                        rs.getInt("JobID"),
                        rs.getInt("CompanyID"),
                        rs.getString("JobTitle"),
                        rs.getString("JobDescription"),
                        rs.getString("JobLocation"),
                        rs.getDouble("Salary"),
                        rs.getString("JobType"),
                        rs.getTimestamp("PostedDate").toLocalDateTime()
                );
                jobs.add(job);
            }
        } catch (DatabaseConnectionException e) {
            System.err.println("Database connection error while inserting job listing: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }

}
