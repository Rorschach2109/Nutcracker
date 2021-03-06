package com.nutcracker.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {
    
	@Override
    public String convertToDatabaseColumn(Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            return "T";
        } else {
            return "F";
        }
    }
    
    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "T".equals(value);
    }
}