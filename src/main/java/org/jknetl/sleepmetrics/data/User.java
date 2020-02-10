package org.jknetl.sleepmetrics.data;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
}
