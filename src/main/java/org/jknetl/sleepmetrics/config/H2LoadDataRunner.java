package org.jknetl.sleepmetrics.config;

import org.jknetl.sleepmetrics.data.Sleep;
import org.jknetl.sleepmetrics.repo.SleepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Component
@Profile("h2")
public class H2LoadDataRunner implements ApplicationRunner {

    public static final int NUMBER_OF_RECORDS = 20;

    private SleepRepository repository;

    @Autowired
    public H2LoadDataRunner(SleepRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
            Sleep s = new Sleep();
            Random random = new Random();
            LocalDate day = now.minusDays(i + 1).toLocalDate();
            s.setFrom(LocalDateTime.of(day, LocalTime.of(random.nextInt(2) + 22,random.nextInt(60))));
            s.setTill(LocalDateTime.of(day.plusDays(1), LocalTime.of(7,0)));
            s.setAwake(Duration.ofMinutes(random.nextInt(30)));
            repository.save(s);
        }
    }
}
