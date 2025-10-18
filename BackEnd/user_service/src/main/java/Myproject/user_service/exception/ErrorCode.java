package Myproject.user_service.exception;


import lombok.Getter;

@Getter

public enum ErrorCode {
    ANOTHER_EXCEPTION(9999,"another exception i don't no"),
    USER_EXITS(1002, "user has been in my sql"),
    USER_NAME_INVALID(1003,"user  name might be has more 3 characters"),
    PASSWORRD_INVALID(1004,"password might be has more 6 characters"),
    USER_NOTFIND(1005," do not user in my sql"),
    NOT_AUTHENTICATED(1006,"not authenticated");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
