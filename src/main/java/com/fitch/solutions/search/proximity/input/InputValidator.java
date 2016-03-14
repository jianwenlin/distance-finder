package com.fitch.solutions.search.proximity.input;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputValidator {

    private final static int MIN_DISTANCE = 0;

    private final static int INPUT_PARM_NUMBER = 4;

    public ValidationMessage validateInput(SearchInput searchInput) {

        if (null == searchInput) {
            return ValidationMessage.INVALID_INPUT_PARM_NUMBER;
        }

        for (Field field : searchInput.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> targetType = field.getType();
            Object value = null;
            try {
                value = field.get(searchInput);
            }
            catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Can not parse input", ex);
                return ValidationMessage.INVALID_DEFAULT;
            }
            if (targetType == String.class) {
                if (isNotValid((String) value)) {
                    return ValidationMessage.getValidationMessage(field.getName());
                }
            }

            if (field.getName().equals(ValidationMessage.INVALID_DISTANCE.getKey())) {
                if (null == value || ((Integer) value) < MIN_DISTANCE) {
                    return ValidationMessage.getValidationMessage(field.getName());
                }
                else {

                }
            }

            if (field.getName().equals(ValidationMessage.INVALID_INPUT_PARM_NUMBER.getKey())) {
                if (null == value || ((Integer) value) != INPUT_PARM_NUMBER) {
                    return ValidationMessage.getValidationMessage(field.getName());
                }
                else {

                }
            }
        }

        return ValidationMessage.VALID;
    }

    private boolean isNotValid(String input) {
        return (null == input || input.trim().isEmpty());
    }
}