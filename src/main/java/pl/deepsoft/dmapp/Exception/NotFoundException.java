package pl.deepsoft.dmapp.Exception;

import pl.deepsoft.dmapp.Service.impl.UserIMPL;

public class NotFoundException extends AppException{
    public NotFoundException() {super("Email nie istnieje");}

    public NotFoundException (String message) {super(message);}
}
