package openLegacy.shop.advice;

import openLegacy.shop.Exceptions.AlreadyExistsException;
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
    public ErrorDetail handleNotFoundException(Exception err) {
        return new ErrorDetail("Not Found error" , err.getMessage());
    }
    @ExceptionHandler(value = {IllegalRequestException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleIllegalRequestException(Exception err) {
        return new ErrorDetail("Illegal Request error", err.getMessage());
    }
    @ExceptionHandler(value = {AlreadyExistsException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleAlreadyExistsException(Exception err) {
        return new ErrorDetail("Already Exists error", err.getMessage());
    }

}
