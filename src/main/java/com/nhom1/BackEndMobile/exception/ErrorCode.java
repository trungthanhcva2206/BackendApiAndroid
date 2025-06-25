package com.nhom1.BackEndMobile.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("Uncategorized Exception", 400),
    INVALID_KEY("Invalid message key", 1001),
    USER_EXISTED("User existed", 400),
    USER_NOT_FOUND("User not found", 404),
    USERNAME_INVALID("Username must be at least 3 characters", 400),
    PASSWORD_INVALID("Password must be at least 8 characters", 404),
    UNAUTHORIZED("Unauthorized", 401),
    COURSE_NOT_FOUND("Course not found", 404),
    DEPARTMENT_NOT_FOUND("Department not found", 404),
    DEPARTMENT_EXISTED("Department existed", 409),
    LESSON_NOT_FOUND("Lesson not found", 404),
    VIDEO_NOT_FOUND("Video not found", 404),
    DOCUMENT_NOT_FOUND("Document not found", 404),
    DUPLICATE_COURSE_REGISTRATION("Duplicate course registration", 400),
    NOT_FOUND("Course not found", 404),
    ;

    ErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private String message;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
