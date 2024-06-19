package com.booking.airline.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class BookingIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "BOOK-ID-";
    private static int currentValue = 10;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return PREFIX+CustomDateTimeFormatter.getFormattedYearDate()+(++currentValue);
    }
}
