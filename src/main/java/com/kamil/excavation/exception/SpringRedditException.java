package com.kamil.excavation.exception;

import org.hibernate.engine.internal.ImmutableEntityEntry;

public class SpringRedditException extends RuntimeException {

    public SpringRedditException(String message) {

        super(message);
    }
}
