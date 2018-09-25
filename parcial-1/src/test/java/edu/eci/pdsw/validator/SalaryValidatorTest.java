package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
//import static org.quicktheories.generators.SourceDSL.*;

import java.util.Optional;

import org.junit.Test;

import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {
		//validator.validate(null);
		GeneradorEmpleados empleadoss = new GeneradorEmpleados();
		qt()
			.forAll(empleadoss.empleados())
			.check(empleado -> {
				Optional<ErrorType> tipo= validator.validate(empleado);
				
				if (empleado.getPersonId()>100000) {
					return Optional.of(ErrorType.INVALID_ID).equals(tipo);
				}else if(empleado.getPersonId()<1000) {
					return Optional.of(ErrorType.INVALID_ID).equals(tipo);
				}else if(empleado.getSalary()>50000) {
					return Optional.of(ErrorType.INVALID_SALARY).equals(tipo);
				}else if(empleado.getSalary()<100) {
					return Optional.of(ErrorType.INVALID_SALARY).equals(tipo);
				}else if(empleado.getSocialSecurityType().equals(SocialSecurityType.EPS) && empleado.getSalary()>=10000) {
					return Optional.of(ErrorType.INVALID_EPS_AFFILIATION).equals(tipo);
				}else if (empleado.getSocialSecurityType().equals(SocialSecurityType.SISBEN) && empleado.getSalary()>=1500) {
					return Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION).equals(tipo);
				}else if(empleado.getSocialSecurityType().equals(SocialSecurityType.PREPAID) && empleado.getSalary()<10000) {
					return Optional.of(ErrorType.INVALID_PREPAID_AFFILIATION).equals(tipo);
				}else if(tipo.equals(Optional.empty())) {
					return true;
				}else {
					return false;
				}
				
				
			});
		
	}
}
