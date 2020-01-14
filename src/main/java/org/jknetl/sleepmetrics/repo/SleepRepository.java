package org.jknetl.sleepmetrics.repo;

import org.jknetl.sleepmetrics.data.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
}
