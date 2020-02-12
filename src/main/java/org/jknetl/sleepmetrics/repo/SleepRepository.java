package org.jknetl.sleepmetrics.repo;

import org.jknetl.sleepmetrics.data.Sleep;
import org.jknetl.sleepmetrics.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findByUser(User user);
}
