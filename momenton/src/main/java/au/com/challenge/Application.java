package au.com.challenge;

import au.com.challenge.dto.Company;
import au.com.challenge.dto.Employee;

/**
 * Main class to start the program
 * 
 * @author Preetham
 *
 */
public class Application {
	public static void main(String[] args) {
		//Create the company with CEO as employee
		Company company = new Company(new Employee("Jamie", 150, null));

		//Recruit remaining employees
		company.recruitEmployee(new Employee("Alan", 100, 150));
		company.recruitEmployee(new Employee("Martin", 220, 100));
		company.recruitEmployee(new Employee("Alex", 275, 100));
		company.recruitEmployee(new Employee("Steve", 400, 150));
		company.recruitEmployee(new Employee("David", 190, 400));

		//Print the company employee hierarchy
		System.out.println("======= Company Employee Hierarchy ========");
		company.printEmployeeHierarchy();
	}
}
