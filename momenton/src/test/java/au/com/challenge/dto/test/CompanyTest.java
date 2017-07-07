package au.com.challenge.dto.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import au.com.challenge.dto.Company;
import au.com.challenge.dto.Employee;
import au.com.challenge.exception.InvalidCEOException;
import au.com.challenge.exception.InvalidEmployeeException;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	private Company company;

	@Before
	public void createCompany() {
		company = new Company(new Employee("Manager", 1, null));
	}

	@Test()
	public void testCreateCompanyWithNullCeo() {
		exception.expect(InvalidCEOException.class);
		exception.expectMessage("CEO cannot be null");
		new Company(null);
	}

	@Test()
	public void testCreateCompanyWithCeoWithManager() {
		exception.expect(InvalidCEOException.class);
		exception.expectMessage("CEO cannot have a manager");
		new Company(new Employee("Manager", 1, 2));
	}

	@Test
	public void testRecruitEmployeeWithNullManager() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Invalid manager id null");
		company.recruitEmployee(new Employee("Employee", 2, null));
	}

	@Test
	public void testRecruitEmployeeWithNegativeManagerId() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Invalid manager id -1");
		company.recruitEmployee(new Employee("Employee", 2, -1));
	}

	@Test
	public void testRecruitEmployeeWithInvalidManagerId() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Manager with id 3 is not present for this employee with id 2");
		company.recruitEmployee(new Employee("Employee", 2, 3));
	}

	@Test
	public void testRecruitEmployeeWithDuplicateEmployee() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Employee with id 2 already exists");
		company.recruitEmployee(new Employee("Employee", 2, 1));
		company.recruitEmployee(new Employee("Employee", 2, 1));
	}
	
	@Test
	public void testRecruitEmployeeWithNullEmployee() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Employee cannot be null");
		company.recruitEmployee(null);
	}
	
	@Test
	public void testGetReportees() {
		Employee emp = new Employee("Employee", 2, 1);
		Employee emp1= new Employee("Employee", 3, 2);
		Employee emp2 = new Employee("Employee", 4, 2);
		Set<Employee> expectedSet = new HashSet<Employee>();
		expectedSet.add(emp1);
		expectedSet.add(emp2);
		company.recruitEmployee(emp);
		company.recruitEmployee(emp1);
		company.recruitEmployee(emp2);
		company.recruitEmployee(new Employee("Employee", 5, 1));
		Set<Employee> set = company.getReportees(emp);
		assertEquals(set,expectedSet);
	}
	
	@Test
	public void testPrintHeirarchy() {
		Employee emp = new Employee("Employee", 2, 1);
		Employee emp1= new Employee("Employee", 3, 2);
		Employee emp2 = new Employee("Employee", 4, 2);
		Set<Employee> expectedSet = new HashSet<Employee>();
		expectedSet.add(emp1);
		expectedSet.add(emp2);
		company.recruitEmployee(emp);
		company.recruitEmployee(emp1);
		company.recruitEmployee(emp2);
		company.recruitEmployee(new Employee("Employee", 5, 1));
		company.printEmployeeHierarchy();
	}
}
