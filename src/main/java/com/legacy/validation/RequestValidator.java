package com.legacy.validation;

import com.legacy.exception.InvalidRequestException;

public class RequestValidator {
    public void validateRequest(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new InvalidRequestException("File path is required");
        }
    }

    public void validateRequest(String rootDirectory) {
        if (rootDirectory == null || rootDirectory.isEmpty()) {
            throw new InvalidRequestException("Root directory is required");
        }
    }
}
