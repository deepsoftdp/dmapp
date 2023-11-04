package pl.deepsoft.dmapp.Exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private AppExceptionDto notFound(NotFoundException x)
    {
        return new AppExceptionDto(x.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private AppExceptionDto badRequest(BadRequestException x)
    {
        return new AppExceptionDto(x.getMessage());
    }
    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private AppExceptionDto notAcceptable(NotAcceptableException x)
    {
        return new AppExceptionDto(x.getMessage());
    }
}
