package com.HansonMotors.HansonMotorsWorkshop.app.exception.exceptionClasses;

public class ApiErrorVO {
 
	
    private String errorCode="VALIDATION_ERROR";
	
	
    private String fieldName;
    
    private String message;
 
    public ApiErrorVO( String message,String fieldName) {
     
        this.message = message;
        this.fieldName=fieldName;
    }

	public String getErrorCode() {
		return errorCode;
	}


	public String getFieldName() {
		return fieldName;
	}

	

	public String getMessage() {
		return message;
	}

	
    
    
    
}