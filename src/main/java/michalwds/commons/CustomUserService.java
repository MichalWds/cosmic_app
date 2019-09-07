package michalwds.commons;


import michalwds.commons.security.CustomUserDetails;
import michalwds.commons.security.UserAppRepository;
import michalwds.models.UserApp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {

    private UserAppRepository userAppRepository;

    public CustomUserService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<UserApp> userAppOptional = userAppRepository.findUserAppByName(name); //if i get user, do something
        userAppOptional.orElseThrow(() -> new UsernameNotFoundException("user not find")); //if user didn't find, function anonymous

        return userAppOptional.map(CustomUserDetails::new).get(); //if found user, create new, and get optional
    }
}
