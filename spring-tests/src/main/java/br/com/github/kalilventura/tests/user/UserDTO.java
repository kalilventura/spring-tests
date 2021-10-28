package br.com.github.kalilventura.tests.user;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
public class UserDTO implements Serializable {
    private Long userId;
    @NotNull
    private String name;
    @NotNull
    private String email;

    public UserDTO() {
    }

    public UserDTO(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public UserDTO(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
