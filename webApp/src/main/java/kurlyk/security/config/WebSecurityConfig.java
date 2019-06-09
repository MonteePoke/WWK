package kurlyk.security.config;

import kurlyk.security.filters.TokenAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@ComponentScan("kurlyk")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/usvers/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/lab-work/**").permitAll()
                .antMatchers("/lab-works/**").permitAll()
                .antMatchers("/task/**").permitAll()
                .antMatchers("/tasks/**").permitAll()
                .antMatchers("/question/**").permitAll()
                .antMatchers("/questions/**").permitAll()
                .antMatchers("/h2-console/**").permitAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
}
