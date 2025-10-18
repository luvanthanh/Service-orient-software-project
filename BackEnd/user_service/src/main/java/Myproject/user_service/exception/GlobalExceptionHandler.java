package Myproject.user_service.exception;

import Myproject.user_service.dto.reponse.ApiReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiReponse> handleException(RuntimeException e){
        ApiReponse  reponseAPI = new ApiReponse();

        reponseAPI.setCode(ErrorCode.ANOTHER_EXCEPTION.getCode());
        reponseAPI.setMessage(ErrorCode.ANOTHER_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(reponseAPI);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiReponse> handleAppException(AppException e){
        ApiReponse  reponseAPI = new ApiReponse();
        ErrorCode errorCode = e.getErrorCode();

        reponseAPI.setCode(errorCode.getCode());
        reponseAPI.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(reponseAPI);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiReponse> handleValidException(MethodArgumentNotValidException e){
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiReponse  reponseAPI = new ApiReponse<>();
        reponseAPI.setCode(errorCode.getCode());
        reponseAPI.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(reponseAPI);
    }
}
