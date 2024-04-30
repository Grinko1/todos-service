package com.todo.app.exceptions;

public class NotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Long fieldValue;

    public NotFoundException( String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s with %s: %d wasn't found", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
