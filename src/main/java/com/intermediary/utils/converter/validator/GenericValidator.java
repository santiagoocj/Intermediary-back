package com.intermediary.utils.converter.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class GenericValidator<T> {
	public void validate(T entity) throws BindException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		for (ConstraintViolation<T> violation : validator.validate(entity)) {
			throw new BindException("error de validación", violation.getMessage());
		}
	}
}
