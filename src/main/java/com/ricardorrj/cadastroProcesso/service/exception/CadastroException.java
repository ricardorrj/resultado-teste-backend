package com.ricardorrj.cadastroProcesso.service.exception;

public class CadastroException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    public CadastroException(String message, Throwable cause) {
        super(message, cause);
    }

    public CadastroException(String message) {
        super(message);
    }
}
