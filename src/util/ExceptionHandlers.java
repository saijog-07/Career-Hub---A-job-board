package util;

import exception.*;
import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class ExceptionHandlers {

 public void validateEmail(String email) throws InvalidEmailFormatException {
     if (email == null || !email.contains("@") || !email.matches("^.+@.+\\..+$")) {
         throw new InvalidEmailFormatException("Invalid email format: " + email);
     }
 }

 public double calculateAverageSalary(List<Double> salaries) throws NegativeSalaryException {
     double total = 0;
     int count = 0;
     for (double salary : salaries) {
         if (salary < 0) {
             throw new NegativeSalaryException("Invalid negative salary found: " + salary);
         }
         total += salary;
         count++;
     }
     return count == 0 ? 0 : total / count;
 }

 public void uploadResume(File file) throws FileUploadException {
     if (!file.exists()) {
         throw new FileUploadException("File not found: " + file.getName());
     }
     if (file.length() > 2 * 1024 * 1024) { // 2MB size limit
         throw new FileUploadException("File size exceeds 2MB: " + file.getName());
     }
     if (!file.getName().endsWith(".pdf") && !file.getName().endsWith(".docx")) {
         throw new FileUploadException("Unsupported file format: " + file.getName());
     }
     // proceed with upload
 }

 public void checkApplicationDeadline(LocalDate deadline) throws ApplicationDeadlineException {
     if (LocalDate.now().isAfter(deadline)) {
         throw new ApplicationDeadlineException("Application deadline has passed: " + deadline);
     }
 }

 public Connection connectToDatabase(String url, String user, String password) throws DatabaseConnectionException {
     try {
         return DriverManager.getConnection(url, user, password);
     } catch (SQLException e) {
         throw new DatabaseConnectionException("Database connection failed: " + e.getMessage());
     }
 }
}
