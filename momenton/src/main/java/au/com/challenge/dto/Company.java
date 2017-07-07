package au.com.challenge.dto;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import au.com.challenge.exception.InvalidCEOException;
import au.com.challenge.exception.InvalidEmployeeException;

/**
 * @author Preetham
 *
 */
public class Company {

	// To store unique employees(without duplicate id's)
	private Set<Employee> employees = new LinkedHashSet<Employee>();

	// Company creation with CEO
	public Company(Employee ceo) {
		if (ceo == null) {
			throw new InvalidCEOException("CEO cannot be null");
		} else if (ceo.getManagerId() != null) {
			throw new InvalidCEOException("CEO cannot have a manager");
		}
		employees.add(ceo);
	}

	/**
	 * This method is used to add employee to employees set.
	 * 
	 * @param employee
	 */
	public void recruitEmployee(Employee employee) {
		if (employee == null) {
			throw new InvalidEmployeeException("Employee cannot be null");
		}
		if (employee.getManagerId() == null || employee.getManagerId() <= 0) {
			throw new InvalidEmployeeException("Invalid manager id " + employee.getManagerId());
		}
		if (!checkIfManagerPresent(employee)) {
			throw new InvalidEmployeeException("Manager with id " + employee.getManagerId()
					+ " is not present for this employee with id " + employee.getId());
		}
		boolean isAdded = employees.add(employee);
		if (!isAdded) {
			throw new InvalidEmployeeException(
					"Employee with id " + employee.getId() + " already exists. ");
		}
	}

	/**
	 * @param employee
	 * @return true if the manager is present, else return false
	 */
	private boolean checkIfManagerPresent(Employee employee) {
		return employees.stream().filter(e -> e.getId().equals(employee.getManagerId()))
				.count() == 1;
	}

	/**
	 * This method is used to print the employee hierarchy
	 */
	public void printEmployeeHierarchy() {
		// Start from the first element in the set which is CEO and start printing the reportees
		printReportees(employees.iterator().next(), 0);

	}

	/**
	 * This method is used to print the employee hierarchy (employee and his reportees)
	 * 
	 * @param employee
	 * @param posistion - Position of the employee
	 */
	private void printReportees(Employee employee, int posistion) {
		System.out.println(getPosistion(posistion).toString() + employee.getName());
		Set<Employee> reportees = getReportees(employee);
		for (Employee reportee : reportees) {
			printReportees(reportee, posistion + 1);
		}

	}

	/**
	 * This method is to get reportees
	 * 
	 * @param emp - Employee who can be a manager
	 * @return set of reportees of emp Employee
	 */
	public Set<Employee> getReportees(Employee emp) {
		return employees.stream().filter((employee) -> {
			if (employee.getManagerId() == null) {
				return false;
			} else {
				return employee.getManagerId().equals(emp.getId());
			}
		}).collect(Collectors.toSet());
	}

	/**
	 * This method is to determine the posistion of the employee and add tabs accordingly
	 * 
	 * @param posistion
	 * @return String builder with tabs or empty string
	 */
	private StringBuilder getPosistion(int posistion) {
		StringBuilder builder = new StringBuilder("");
		if (posistion == 0) {
			return builder;
		}
		for (int i = 0; i < posistion; i++) {
			builder.append("\t");
		}
		return builder;
	}
}
