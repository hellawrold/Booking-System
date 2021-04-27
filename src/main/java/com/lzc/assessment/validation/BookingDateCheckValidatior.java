package com.lzc.assessment.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lzc.assessment.entity.BookApplication;


public class BookingDateCheckValidatior implements ConstraintValidator<BookingDateCheck, BookApplication> {

	private boolean require = false;
	
	@Override
	public boolean isValid(BookApplication bookApplication, ConstraintValidatorContext context) {
		System.out.println("Validating........");
		return bookApplication.getStart_time() != bookApplication.getEnd_time();
	}
	





}
