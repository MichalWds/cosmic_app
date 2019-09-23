package michalwds;

/**
 * @Author Michal Wadas
 * @SpringBootApplication contains all annotations below:
 * @Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class CosmicAppApplication {

    //Run queries before run app to create password

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Static method which run  SpringApplication's static method : "run"
     * @param args parameter of running class
     */
    public static void main(String[] args) {
        SpringApplication.run(CosmicAppApplication.class, args);
    }
}
