package au.com.challenge.dto.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import au.com.challenge.dto.Employee;
import au.com.challenge.exception.InvalidEmployeeException;

public class EmployeeTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testEmployeeWithEmptyName() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Name field cannot be null or empty");
		Employee emp = new Employee("", 1, 2);

	}

	@Test
	public void testEmployeeWithNullName() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Name field cannot be null or empty");
		Employee emp = new Employee(null, 1, 2);
	}
	
	@Test
	public void testEmployeeWithEmptySpaceName() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Name field cannot be null or empty");
		Employee emp = new Employee("    ", 1, 2);
	}
	
	@Test
	public void testEmployeeWithNegativeId() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Employee id field cannot be null or less than or equal to 0");
		Employee emp = new Employee("Test", -1, 2);
	}
	
	@Test
	public void testEmployeeWithNullId() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Employee id field cannot be null or less than or equal to 0");
		Employee emp = new Employee("Test", null, 2);
	}
	
	@Test
	public void testEmployeeWithZeroId() {
		exception.expect(InvalidEmployeeException.class);
		exception.expectMessage("Employee id field cannot be null or less than or equal to 0");
		Employee emp = new Employee("Test", 0, 2);
	}
	
	@Test
	public void testEmployeeWithValidIdAndName() {
		Employee emp = new Employee("Test", 1, 2);
		assertEquals(emp.getName(),"Test");
		assertEquals(emp.getId(),new Integer(1));
		assertEquals(emp.getManagerId(),new Integer(2));
	}

}
