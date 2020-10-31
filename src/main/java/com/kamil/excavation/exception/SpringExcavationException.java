package com.kamil.excavation.exception;

import org.hibernate.engine.internal.ImmutableEntityEntry;

public class SpringExcavationException extends RuntimeException {

    public SpringExcavationException(String message) {

        super(message);
    }
}
