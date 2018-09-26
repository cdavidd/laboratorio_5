package edu.eci.pdsw.validator;

//import java.awt.List;

import org.quicktheories.core.*;
import org.quicktheories.generators.*;
//import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;
import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

public class GeneradorEmpleados {
	public Gen<Employee> empleados(){
		return 	ids().zip(salario(),seguridadSocial(), (id,salario,seguridad) -> new Employee(id,salario,seguridad));
	}
	
	private Gen<Integer> ids(){
		return integers().between(1000, 100000);
	}
	private Gen<Integer> salario(){
		return integers().between(100, 50000);
	}
	
	private Gen<SocialSecurityType> seguridadSocial(){
		return Generate.enumValues(SocialSecurityType.class);
	}
	
}
