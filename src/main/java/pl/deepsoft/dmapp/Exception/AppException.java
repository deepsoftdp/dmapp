package pl.deepsoft.dmapp.Exception;

public class AppException extends RuntimeException {

    public AppException(String message){
        super(message);
    }

    public String toString() {
        return getMessage();
    }
}
