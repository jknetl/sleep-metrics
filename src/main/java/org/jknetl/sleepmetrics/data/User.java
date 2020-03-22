package org.jknetl.sleepmetrics.data;

import lombok.Data;
import lombok.ToString;
import org.jknetl.sleepmetrics.config.UniqueField;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true, length = 30)
    @Size(min = 4, max = 30)
    @UniqueField(field = "username")
    private String username;
    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Column(unique = true)
    @Email
    @UniqueField(field = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Sleep> sleepRecords;
}
