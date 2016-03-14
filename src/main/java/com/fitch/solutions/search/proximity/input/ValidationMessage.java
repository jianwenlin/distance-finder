package com.fitch.solutions.search.proximity.input;

import java.util.HashMap;
import java.util.Map;

public enum ValidationMessage {

    INVALID_KEYWORD1("keyword1", "Invalid keyword 1 - Keyword should be a regular word"),
    INVALID_KEYWORD2("keyword2", "Invalid keyword 2 - Keyword should be a regular word"),
    INVALID_FILE_NAME("fileName",
            "File not found or invalid - input file should be palced in current directory or classpath directory"),
    INVALID_DISTANCE("distance", "Invalid distance specified -- distance should be greater than zero"),
    INVALID_INPUT_PARM_NUMBER("inputParmNumber",
            "Run program with javac fitch-search-proximity keyword1 keyword2 distance input_file"),
    VALID("valid", "input is good"),
    INVALID_DEFAULT("default",
            "Program runs into a problem. Run program with javac fitch-search-proximity keyword1 keyword2 distance input_file");

    private String key;

    private String errorMessage;

    private static Map<String, ValidationMessage> messageMap = createMap();

    private static Map<String, ValidationMessage> createMap() {
        Map<String, ValidationMessage> messageMapLocal = new HashMap<String, ValidationMessage>();
        for (ValidationMessage validationMessage : values()) {
            messageMapLocal.put(validationMessage.getKey(), validationMessage);
        }
        return messageMapLocal;
    }

    private ValidationMessage(String key, String errorMessage) {
        this.key = key;
        this.errorMessage = errorMessage;
    }

    public static ValidationMessage getValidationMessage(String key) {
        return messageMap.get(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
