package recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import recipes.security.auth.ApplicationUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    ApplicationUserService applicationUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/api/register", "/actuator/shutdown", "/h2").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(applicationUserService)
                .passwordEncoder(getEncoder());
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}