package pl.deepsoft.dmapp.Exception;

public class NotAcceptableException extends AppException{
    public NotAcceptableException () {super("Dane są nieprawidłowe");}

    public NotAcceptableException (String message) {super(message);}


}
