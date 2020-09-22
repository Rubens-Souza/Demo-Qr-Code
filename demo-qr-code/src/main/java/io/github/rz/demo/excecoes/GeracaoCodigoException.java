package io.github.rz.demo.excecoes;

public class GeracaoCodigoException extends BusinessException {

	private static final long serialVersionUID = 7424336740996943931L;

	public GeracaoCodigoException() {
        super();
    }

    public GeracaoCodigoException(Throwable cause) {
        super(cause);
    }

    public GeracaoCodigoException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeracaoCodigoException(String message) {
        super(message);
    }

    public GeracaoCodigoException(Integer code, String message) {
        super(message);
    }
}
