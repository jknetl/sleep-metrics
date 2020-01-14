package org.jknetl.sleepmetrics.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "startedAt")
    private LocalDateTime from;
    @NotNull
    @Column(name = "finishedAt")
    private LocalDateTime till;

    private Duration awake = Duration.ZERO; // how long you were awake during the sleep time

}
