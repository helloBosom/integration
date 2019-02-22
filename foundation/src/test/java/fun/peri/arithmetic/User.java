package fun.peri.arithmetic;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String username;
    private String password;
    private String name;
    private String email;

    public User() {

    }

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
