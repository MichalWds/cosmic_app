package michalwds.commons.security;

//can not use User because Spring Security has class with name User so it will be a problem

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user") //schema ="public") jak nie w myysql
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto
    private int id;

    private String name;
    private String password;
    private int active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)       //lazer nam nie daej mozliwosci logowania// eger wyciaga wszystkie dane
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id")
    )
    private Set<Role> roles;

    public UserApp(UserApp userApp){
        this.name = userApp.getName();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();
        this.roles = userApp.getRoles();
}
}