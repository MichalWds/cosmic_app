package michalwds.commons.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity          //pre authorized use when give role directly to methods
@EnableGlobalMethodSecurity(prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserService customUserService, PasswordEncoder passwordEncoder) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable();

        http
                .authorizeRequests()  //włączanie usług autoryzacji
                .antMatchers("/login**").permitAll() //** bierze pod uwage request parametry, dostepny dla wszystkich (permit all)
                .and()
                .formLogin() //formularz logowania automatycznie ustawiany przez SpringSecurity
                .loginPage("/login")
                .loginProcessingUrl("/signin")  /*dotyczy atrybutu w <form>*/ //post
                .usernameParameter("username") //parametr do bindowania do formularza (mapowanie)
                .passwordParameter("password")
                //or defaultSuccessUrl("/"), gdy login sie zgadza, przelogowuje np na strone, prawie to samo co succesHandler
                /*GOOD LOGIN */
                .successHandler((req, res, auth ) -> {
                    for (GrantedAuthority authority : auth.getAuthorities()) { //lista typow autoryzacji, np user, admin
                        System.out.println(authority.getAuthority());
                    }
                    System.out.println(auth.getName());
                    res.sendRedirect("/"); /*home page*/
                })
                /*FAILED LOGIN */
               //.failureUrl("/")  == defaultSuccessUrl
                .failureHandler((req, res, exception) -> {
                    String errorMessage;
                    if(exception.getClass().isAssignableFrom(BadCredentialsException.class)){
                        errorMessage = "Invalid username of password";
                        exception.getMessage();
                    }else {
                        errorMessage = "Unknown error" + exception.getMessage();
                    }
                        req.getSession().setAttribute("message", errorMessage);
                        res.sendRedirect("/login");

                })
                 .permitAll()
                 .and()
                 .logout()
                 .logoutUrl("/logout") //endpoint
                 .logoutSuccessHandler((req, res, auth) -> {
                     res.sendRedirect("/login");
                 })
                 .permitAll()
                 .and()
                 .csrf().disable(); //cross site request forgery metoda ataku na serwis internetowy,

                http.headers().frameOptions().disable(); //for H2, resetuje DB
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* */
        auth.userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder); //unhash password
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
