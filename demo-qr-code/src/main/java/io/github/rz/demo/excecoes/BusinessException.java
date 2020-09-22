package io.github.rz.demo.excecoes;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 403395282301044725L;
	private final Integer code;

    public BusinessException() {
        super();
        this.code = null;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.code = null;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = null;
    }

    public BusinessException(String message) {
        super(message);
        this.code = null;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
