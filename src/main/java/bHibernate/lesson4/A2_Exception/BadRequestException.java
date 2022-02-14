package bHibernate.lesson4.A2_Exception;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
