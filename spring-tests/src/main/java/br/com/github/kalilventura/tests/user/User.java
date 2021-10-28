package br.com.github.kalilventura.tests.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private LocalDateTime registrationDate;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
