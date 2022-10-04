package com.inceptustechnology.tracc.plan.server.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    protected LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) {
		String pattern1 = "yyyy/MM/dd HH:mm:ss";
		String pattern2 = "MM/dd/yyyy HH:mm:ss";
		String pattern3 = "MM/dd/yyyy h:m:s a";
		String pattern4 = "M/d/yyyy h:m:s a";
		try {
			String date = jp.readValueAs(String.class);
			LocalDateTime parsedDate = null;
			if ((parsedDate = paseISODate(date)) != null) {
				return parsedDate;
			} else if ((parsedDate = parseDate(pattern1, date)) != null) {
				return parsedDate;
			} else if ((parsedDate = parseDate(pattern2, date)) != null) {
				return parsedDate;
			} else if ((parsedDate = parseDate(pattern3, date)) != null) {
				return parsedDate;
			} else if ((parsedDate = parseDate(pattern4, date)) != null) {
				return parsedDate;
			} else {
				System.out.println("ERROR ON PARSE DATE "+ date);
				return null;
			}
		} catch (IOException e) {
			System.out.println("ERROR ON READ VALUE"+ e.getClass()+e.getMessage());
			return null;
		}
	}

	public LocalDateTime paseISODate(String date) {
		try {
			return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC")));
		} catch (Exception e) {
			return null;
		}
	}

	public LocalDateTime parseDate(String pattern, String date) {
		try {
			return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("UTC")));
		} catch (Exception e1) {
			return null;
		}
	}
}