
package com.HansonMotors.HansonMotorsWorkshop.app.exception.exceptionClasses;



public class AuthenticationFailureException extends GenericServiceException {

	
	public AuthenticationFailureException( String error, String message) {
		super("username", error, message);		
	}

	private static final long serialVersionUID = 8847218964430729266L;

}
