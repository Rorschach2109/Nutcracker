package com.nutcracker.model.converters;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDateTime) {
		return (null == localDateTime ? null : Date.valueOf(localDateTime));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlTimestamp) {
		return (null == sqlTimestamp ? null : sqlTimestamp.toLocalDate());
	}

}
