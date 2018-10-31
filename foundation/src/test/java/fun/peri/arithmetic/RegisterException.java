package fun.peri.arithmetic;

public class RegisterException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -163288048983105152L;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;

    }

    public RegisterException() {

    }

    public RegisterException(String message) {
        this.message = message;
    }

    public String displayException(String message) {
        return ("exception message" + message);
    }

}
