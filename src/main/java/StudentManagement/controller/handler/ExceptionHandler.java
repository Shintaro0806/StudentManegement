package StudentManagement.controller.handler;

import StudentManagement.exception.TestException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 例外処理を行うクラスです。
 */
@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(TestException.class)
  public ResponseEntity<Map<String, Object>> handleTestException(TestException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Bad Request");
    response.put("message", ex.getMessage());

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(response);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(TypeMismatchException.class)
  public ResponseEntity<Map<String, Object>> handleTypeMismatchException(TypeMismatchException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Bad Request");
    response.put("message", "IDは数値で指定してください。");

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(response);
  }
}


