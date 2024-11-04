package sk.pocsik.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "\"user\"")
public class User extends BaseEntity {

    private String username;
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
