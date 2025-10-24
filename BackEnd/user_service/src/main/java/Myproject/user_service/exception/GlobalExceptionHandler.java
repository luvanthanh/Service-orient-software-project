package Myproject.user_service.exception;

import Myproject.user_service.dto.reponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(RuntimeException e){
        ApiResponse  responseAPI = new ApiResponse();

        responseAPI.setCode(ErrorCode.ANOTHER_EXCEPTION.getCode());
        responseAPI.setMessage(ErrorCode.ANOTHER_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(responseAPI);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException e){
        ApiResponse  responseAPI = new ApiResponse();
        ErrorCode errorCode = e.getErrorCode();

        responseAPI.setCode(errorCode.getCode());
        responseAPI.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(responseAPI);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidException(MethodArgumentNotValidException e){
        String enumKey = e.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiResponse  responseAPI = new ApiResponse<>();
        responseAPI.setCode(errorCode.getCode());
        responseAPI.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(responseAPI);
    }
}
