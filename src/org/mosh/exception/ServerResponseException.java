package org.mosh.exception;

/**
 * Created by crodriguez on 15/05/15.
 */
public class ServerResponseException extends Exception {

    private static final String ERROR_MESSAGE = "Server Response Exception";

    public ServerResponseException(){
        super(ERROR_MESSAGE);
    }

    public ServerResponseException(Exception e){
        super(ERROR_MESSAGE, e);
    }
}
