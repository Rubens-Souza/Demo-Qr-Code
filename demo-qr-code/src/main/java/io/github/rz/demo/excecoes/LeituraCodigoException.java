package io.github.rz.demo.excecoes;

public class LeituraCodigoException extends BusinessException {

	private static final long serialVersionUID = -2737462683794002327L;

	public LeituraCodigoException() {
        super();
    }

    public LeituraCodigoException(Throwable cause) {
        super(cause);
    }

    public LeituraCodigoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeituraCodigoException(String message) {
        super(message);
    }

    public LeituraCodigoException(Integer code, String message) {
        super(message);
    }
}
