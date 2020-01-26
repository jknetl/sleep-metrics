package org.jknetl.sleepmetrics.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Duration;
import java.util.Locale;

@Slf4j
public class MinutesDurationFormatter implements Formatter<Duration> {
    @Override
    public Duration parse(String s, Locale locale) throws ParseException {
        long minutes = 0;
        try {
            minutes = Long.valueOf(s);
        } catch (NumberFormatException e) {
            log.warn("Invalid conversion. Value " + s);
        }
        return Duration.ofMinutes(minutes);
    }

    @Override
    public String print(Duration duration, Locale locale) {
        return String.valueOf(duration.toMinutes());
    }
}
