package kr.ac.hansung.controller;

import kr.ac.hansung.exception.OfferNotFoundException;
import kr.ac.hansung.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // 특정한 Controller 가 아니라, 모든 Controller 에서 발생하는 예외처리 등록
public class GlobalExceptionController {

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOfferNotFoundException(HttpServletRequest request, OfferNotFoundException exception) {
        String requestURI = request.getRequestURI();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setRequestURI(requestURI);
        errorResponse.setErrorCode("offer.notfound.exception");
        errorResponse.setErrorMsg("Offer with id " + exception.getOfferId() + " not found");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
