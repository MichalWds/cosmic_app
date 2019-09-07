package michalwds.commons.security;


import michalwds.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {


    @Query(value = "select u from UserApp u where u.name=?1")
    Optional<UserApp> findUserAppByName(String name);  //optional when user is not found

}
