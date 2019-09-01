package michalwds.commons.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Author Michal Wadas
 * Swagger helps us to make visualisation of API
 * and helps to create documentations of API
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *Configuration of Swagger
     *Docket (main class  of API)
     * apis targeting to out controllers
     * path give us a path to endpoints
     */

@Bean
public Docket config(){
    return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("michalwds.controllers"))
                    .paths(regex("api/.*"))
                    .build();
}

}
