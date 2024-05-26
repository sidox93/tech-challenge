package br.com.fiap.api.ais_ecommerce.exception;

public class ControllerNotFundException extends RuntimeException{
    public ControllerNotFundException(String message){
        super(message);
    }
}
