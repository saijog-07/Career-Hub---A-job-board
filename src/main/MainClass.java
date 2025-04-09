package main;

import dao.ServiceProviderImpl;
import entity.model.Applicant;
import entity.model.JobListing;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServiceProviderImpl service = new ServiceProviderImpl();
        int choice;

        do {
            System.out.println("\n===== Career Hub Menu =====");
            System.out.println("1. Create Profile (Applicant)");
            System.out.println("2. Post Job");
            System.out.println("3. Apply to Latest Job");
            System.out.println("4. Apply to Specific Job");
            System.out.println("5. View All Applicants");
            System.out.println("6. View All Job Listings");
            System.out.println("7. Search Jobs by Salary Range");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- Create Applicant Profile ---");
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Resume: ");
                    String resume = scanner.nextLine();

                    service.createProfile(email, firstName, lastName, phone, resume);
                    break;

                case 2:
                    System.out.println("\n--- Post a Job ---");
                    System.out.print("Company ID: ");
                    int companyId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Job Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Job Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Job Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Job Type: ");
                    String type = scanner.nextLine();

                    service.postJob(companyId, title, description, location, salary, type);
                    break;

                case 3:
                    System.out.println("\n--- Apply to Latest Job ---");
                    System.out.print("Applicant ID: ");
                    int applicantId1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Cover Letter: ");
                    String coverLetter1 = scanner.nextLine();

                    service.apply(applicantId1, coverLetter1);
                    break;

                case 4:
                    System.out.println("\n--- Apply to Specific Job ---");
                    System.out.print("Job ID: ");
                    int jobId = scanner.nextInt();
                    System.out.print("Applicant ID: ");
                    int applicantId2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Cover Letter: ");
                    String coverLetter2 = scanner.nextLine();

                    service.applyForJob(jobId, applicantId2, coverLetter2);
                    break;

                case 5:
                    System.out.println("\n--- All Applicants ---");
                    List<Applicant> applicants = service.getApplicants();
                    for (Applicant app : applicants) {
                        System.out.println(app);
                    }
                    break;

                case 6:
                    System.out.println("\n--- All Job Listings ---");
                    List<JobListing> jobs = service.getJobs();
                    for (JobListing job : jobs) {
                        System.out.println(job);
                    }
                    break;
                    
                case 7:
                    System.out.println("\n--- Search Jobs by Salary Range ---");
                    System.out.print("Enter Minimum Salary: ");
                    double minSalary = scanner.nextDouble();
                    System.out.print("Enter Maximum Salary: ");
                    double maxSalary = scanner.nextDouble();

                    List<JobListing> filteredJobs = service.getJobsBySalaryRange(minSalary, maxSalary);
                    if (filteredJobs.isEmpty()) {
                        System.out.println("No jobs found in the specified salary range.");
                    } else {
                        for (JobListing job : filteredJobs) {
                            System.out.println(job);
                        }
                    }
                    break;


                case 0:
                    System.out.println("Exiting...!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
