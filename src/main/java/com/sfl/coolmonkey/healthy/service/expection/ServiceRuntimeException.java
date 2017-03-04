package com.sfl.coolmonkey.healthy.service.expection;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/24/17
 * Time: 6:43 PM
 */
public class ServiceRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 752369715061987944L;

    public ServiceRuntimeException(final String message) {
        super(message);
    }
}
