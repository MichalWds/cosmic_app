package michalwds.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Set;


//can not use User because Spring Security has class with name User so it will be a problem

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    private String name;
    private String password;
    private int active;  //int because in DB true false is just numbers 1/0

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // all if i delete object, also delete role etc// fetch eager means loading object. if lazy means just what needed
    @JoinTable(name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role"))
    private Set<Role> roles;

    public UserApp(UserApp userApp) {   //rekurencja
        this.name = userApp.getName();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();
        this.roles = userApp.getRoles();
    }


}
