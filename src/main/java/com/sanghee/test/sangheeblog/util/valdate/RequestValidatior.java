package com.sanghee.test.sangheeblog.util.valdate;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class RequestValidatior {

	public ResultValidator  validate(BindingResult errorResult) {
		int errorCount = errorResult.getErrorCount();
		List<FieldError> errors = errorResult.getFieldErrors();
		String errorMsg = errorResult.getFieldError().getDefaultMessage();
		String errorField  = errorResult.getFieldError().getField(); 
		String message = errorField+ "에 " + errorMsg;
		StringBuffer fields = new StringBuffer();
		for (int i = 0; i < errors.size(); i++) {
			FieldError error = errors.get(i);
			String field = error.getField();
			if (i == errors.size()-1)
				fields.append(field);
			else
				fields.append(field+",");
		}
		return new ResultValidator(errorCount, message, fields.toString());
	}
	
}

