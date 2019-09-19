package michalwds;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {

    @Test
    public void getPassword() {
        getPassHash();
    }

    @Bean
    public PasswordEncoder getPassHash() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password;

        System.out.println(passwordEncoder.encode("user"));

        return new BCryptPasswordEncoder();
    }


}
