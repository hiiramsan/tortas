package org.itson.bdavanzadas.persistencia.exception;

/**
 *
 * @author Ramosz
 */
public class PersistenciaException extends Exception {

    /**
     *
     */
    public PersistenciaException() {
    }

    /**
     *
     * @param message
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public PersistenciaException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public PersistenciaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
