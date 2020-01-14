package org.jknetl.sleepmetrics.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import java.time.Duration;

@Slf4j
public class MinutesDurationConverter implements Converter<String, Duration> {

    @Override
    public Duration convert(String s) {
        long minutes = 0;
        try {
            minutes = Long.valueOf(s);
        } catch (NumberFormatException e) {
            log.warn("Invalid conversion. Value " + s);
        }
        return Duration.ofMinutes(minutes);
    }
}
