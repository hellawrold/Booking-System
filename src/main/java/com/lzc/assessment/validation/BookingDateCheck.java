package com.lzc.assessment.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookingDateCheckValidatior.class)
public @interface BookingDateCheck {
	String message() default "Start Time Cannot Greater than End Time";
	Class<?>[] groups() default{};
	public abstract Class<? extends Payload>[] payload() default {};

}
