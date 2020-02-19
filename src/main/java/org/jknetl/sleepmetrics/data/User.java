package org.jknetl.sleepmetrics.data;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Sleep> sleepRecords;
}
