package ifactory.utils;

public class IfactoryException extends RuntimeException {
    static final long serialVersionUID = 1L;

    private int resultCode;

    public IfactoryException() {}

    public IfactoryException(int resultCode) {
        this.resultCode = resultCode;
    }

    public IfactoryException(int resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return this.resultCode;
    }
}