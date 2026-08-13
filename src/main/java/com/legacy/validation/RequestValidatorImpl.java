package com.legacy.validation;

import com.legacy.exception.InvalidRequestException;

public class RequestValidatorImpl implements RequestValidator {
    @Override
    public void validateRequest(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new InvalidRequestException("File path is required");
        }
    }

    @Override
    public void validateRequest(String rootDirectory) {
        if (rootDirectory == null || rootDirectory.isEmpty()) {
            throw new InvalidRequestException("Root directory is required");
        }
    }
}
