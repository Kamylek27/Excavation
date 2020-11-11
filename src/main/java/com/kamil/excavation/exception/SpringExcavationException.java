package com.kamil.excavation.exception;

import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.mail.MailException;

public class SpringExcavationException extends RuntimeException {

    public SpringExcavationException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringExcavationException(String exMessage) {
        super(exMessage);
    }
}
