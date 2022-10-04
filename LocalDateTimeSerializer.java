package com.inceptustechnology.tracc.plan.server.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider sp) throws IOException {
        String isoLocalDateTimeString = value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC")));
        gen.writeString(isoLocalDateTimeString);
    }
}
