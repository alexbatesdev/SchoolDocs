package com.example.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlingConfig {

    @ExceptionHandler(NotAllowedInUniverseException.class)
    protected ResponseEntity<ResponseBody> handleNotAllowedInUniverseException(NotAllowedInUniverseException ex, WebRequest request) {
        ResponseBody body = new ResponseBody("PERSON_NOT_ALLOWED", "The person you are attempting to add is not allowed");
        ResponseEntity<ResponseBody> response = new ResponseEntity<ResponseBody>(body, HttpStatus.BAD_REQUEST);
        return response;

    }

    public static class ResponseBody {
        private String message;
        private String code;

        public ResponseBody(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }


    }
}
