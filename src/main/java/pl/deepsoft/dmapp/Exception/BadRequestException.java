package pl.deepsoft.dmapp.Exception;

public class BadRequestException extends AppException{
    public BadRequestException() {super("Email nie istnieje");}

    public BadRequestException (String message) {super(message);}
}
