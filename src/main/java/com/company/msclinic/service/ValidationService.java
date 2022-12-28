package com.company.msclinic.service;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationService {
  private final Validator validator;

  public ValidationService(Validator validator) {
    this.validator = validator;
  }

  public void validate(Object object) {
    Set<ConstraintViolation<Object>> result = validator.validate(object);

    if (!result.isEmpty()) {
      throw new ConstraintViolationException(result);
    }
  }
}
