package org.jknetl.sleepmetrics.data;

import lombok.Data;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime from;
    @NotNull
    @Column(name = "finishedAt")

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime till;

    private Duration awake = Duration.ZERO; // how long you were awake during the sleep time

}
