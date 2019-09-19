package michalwds.commons.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    private String role;

    @JsonIgnore //not make mirror role, loop, no stack overflow while building by hibernate
    @ManyToMany(mappedBy = "roles")
    private Set<UserApp> users = new HashSet<>(); //will return empty role not null

}


