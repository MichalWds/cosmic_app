package michalwds;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author Michal Wadas
 * Class joins servlet, filter and components of ServletContextInitializer
 * with application's context on server.
 * Extending of the class SpringBootServletInitializer allows to configure application while
 * app is running by servlet's container which do this by overriding the method configure()
 *
 **/

public class ServletInitializer extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CosmicAppApplication.class);
    }

}
