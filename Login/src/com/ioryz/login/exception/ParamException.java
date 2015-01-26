package com.ioryz.login.exception;

import java.util.HashMap;
import java.util.Map;

public class ParamException extends Exception {

    private static final long serialVersionUID = 1L;

    private Map<String, String> errorMessages = new HashMap<String, String>();

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String field, String msg) {
        errorMessages.put(field, msg);
    }

    public boolean hasError() {
        return !errorMessages.isEmpty();
    }
}
