package br.com.makersweb.makersfood.domain.exceptions;

import br.com.makersweb.makersfood.domain.validation.handler.Notification;

/**
 * @author aaristides
 */
public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
