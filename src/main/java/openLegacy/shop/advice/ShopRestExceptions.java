package openLegacy.shop.advice;

import openLegacy.shop.Exceptions.IllegalRequestException;
import openLegacy.shop.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ShopRestExceptions {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleNotFoundException(Exception e) {
        return new ErrorDetail("Not Found error" , e.getMessage());
    }
    @ExceptionHandler(value = {IllegalRequestException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleIllegalRequestException(Exception e) {
        return new ErrorDetail("Illegal Request error", e.getMessage());
    }
}
