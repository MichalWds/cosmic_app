package michalwds.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor //lombok gives constructor without arguments
@Data //getters and setters Lombok
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    private String role;

    @JsonIgnore //not make mirror role, loop, no stack overflow while building by hibernate
    @ManyToMany(mappedBy = "roles")
    private Set<UserApp> users = new HashSet<>(); //will return empty role not null
}


