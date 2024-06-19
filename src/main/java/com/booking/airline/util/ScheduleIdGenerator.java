package com.booking.airline.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class ScheduleIdGenerator implements IdentifierGenerator {

    private static final String PREFIX = "SCH-";
    private static int currentValue = 30;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return PREFIX+CustomDateTimeFormatter.getFormattedYearDate()+(++currentValue);
    }
}
