package au.com.challenge.dto;

import au.com.challenge.exception.InvalidEmployeeException;

/**
 * @author Preetham
 *
 */
public class Employee {
	private String name;
	private Integer id;
	private Integer managerId;

	public Employee(String name, Integer id, Integer managerId) {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidEmployeeException("Name field cannot be null or empty");
		}
		if (id == null || id <= 0) {
			throw new InvalidEmployeeException(
					"Employee id field cannot be null or less than or equal to 0");
		}
		this.name = name;
		this.id = id;
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}
	public Integer getId() {
		return id;
	}
	public Integer getManagerId() {
		return managerId;
	}

	//Two employees with same id are considered equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	//Two employees with same id are considered equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
