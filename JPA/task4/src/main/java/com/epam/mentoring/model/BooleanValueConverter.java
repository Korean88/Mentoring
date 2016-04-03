package com.epam.mentoring.model;

import javax.persistence.AttributeConverter;

/**
 * Created by Andrey on 03.04.2016.
 */
public class BooleanValueConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if (Boolean.TRUE.equals(aBoolean)) {
            return "true";
        }
        return "false";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return "true".equals(s);
    }
}
