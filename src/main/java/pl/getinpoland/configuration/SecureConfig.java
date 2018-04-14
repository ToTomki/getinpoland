package pl.getinpoland.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.getinpoland.auth.UserAuth;

@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserAuth userAuth;

    @Autowired
    PersonalisedAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/", "/aboutUs", "/aboutPoland", "/user/register", "/login", "/explore").permitAll()
                    .antMatchers("/logout").hasAnyAuthority("ADMIN", "CHIEF", "REDACTOR", "USER")
                    .antMatchers("/user/register").anonymous()
                    .antMatchers("/resources/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(successHandler)
                    //.successForwardUrl("/loggedin")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/outlogged")
                    .permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        authenticationManagerBuilder.userDetailsService(userAuth).passwordEncoder(encoder);
    }
}
