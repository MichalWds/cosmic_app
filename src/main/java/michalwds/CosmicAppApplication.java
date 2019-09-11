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

import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
@SpringBootApplication
public class CosmicAppApplication {


    /**
     * Static method which run  SpringApplication's static method : "run"
     * @param args parameter of running class
     */
    public static void main(String[] args) {
        SpringApplication.run(CosmicAppApplication.class, args);
    }
}
