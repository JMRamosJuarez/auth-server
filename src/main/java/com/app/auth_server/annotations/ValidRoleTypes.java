package com.app.auth_server.annotations;

import com.app.auth_server.validators.RoleTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoleTypes {
    String message() default "Invalid role type found in the array.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}