package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {

	/**
	 * {@inheritDoc}}
	 */
	public Optional<ErrorType> validate(Employee employee) {
		if (employee.getPersonId()>100000 || employee.getPersonId()<1000){
			return Optional.of(ErrorType.INVALID_ID);
		}else if(employee.getSalary()>50000 || employee.getSalary()<100) {
			return Optional.of(ErrorType.INVALID_SALARY);
		}else if(employee.getSocialSecurityType().equals(SocialSecurityType.EPS) && employee.getSalary()>=10000){
			return Optional.of(ErrorType.INVALID_EPS_AFFILIATION);
		}else if(employee.getSocialSecurityType().equals(SocialSecurityType.SISBEN) && employee.getSalary()>=1500){
			return Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);	
		}else if(employee.getSocialSecurityType().equals(SocialSecurityType.PREPAID) && employee.getSalary()<10000){
			return Optional.of(ErrorType.INVALID_PREPAID_AFFILIATION);
		}else {
			return Optional.empty();
		}
	}
}
