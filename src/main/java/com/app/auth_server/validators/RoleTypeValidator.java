package com.app.auth_server.validators;

import com.app.auth_server.annotations.ValidRoleTypes;
import com.app.auth_server.entities.RoleType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class RoleTypeValidator implements ConstraintValidator<ValidRoleTypes, List<RoleType>> {

    @Override
    public void initialize(ValidRoleTypes constraintAnnotation) {
        // No initialization needed in this case
    }

    @Override
    public boolean isValid(List<RoleType> roleTypes, ConstraintValidatorContext context) {
        if (roleTypes == null || roleTypes.isEmpty()) {
            return false;
        }

        return roleTypes.stream()
                .allMatch(roleType -> roleType != null && Arrays.asList(RoleType.values()).contains(roleType));

    }
}