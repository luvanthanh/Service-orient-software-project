package Myproject.user_service.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter

public enum ErrorCode {
    ANOTHER_EXCEPTION(9999,"another exception i don't no", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXITS(1002, "user has been in my sql", HttpStatus.NOT_ACCEPTABLE),
    USER_NAME_INVALID(1003,"user  name might be has more 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORRD_INVALID(1004,"password might be has more 6 characters", HttpStatus.BAD_REQUEST),
    USER_NOTFIND(1005," do not user in my sql", HttpStatus.BAD_REQUEST),
    NOT_AUTHENTICATED(1006,"not authenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007," you do not have thís permission",HttpStatus.FORBIDDEN)
    ;


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message,  HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
