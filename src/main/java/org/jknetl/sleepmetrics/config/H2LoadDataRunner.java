package org.jknetl.sleepmetrics.config;

import org.jknetl.sleepmetrics.data.Sleep;
import org.jknetl.sleepmetrics.data.User;
import org.jknetl.sleepmetrics.repo.SleepRepository;
import org.jknetl.sleepmetrics.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private SleepRepository sleepRepo;
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @Autowired
    public H2LoadDataRunner(SleepRepository sleepRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.sleepRepo = sleepRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
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
            sleepRepo.save(s);
        }

        User testUser = new User();
        testUser.setUsername(env.getProperty("org.jknetl.sleep-metrics.test-user.username"));
        testUser.setPassword(passwordEncoder.encode(env.getProperty("org.jknetl.sleep-metrics.test-user.password")));
        testUser.setEmail(env.getProperty("org.jknetl.sleep-metrics.test-user.email"));

        userRepo.save(testUser);
    }
}
