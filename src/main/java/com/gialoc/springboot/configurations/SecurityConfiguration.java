package com.gialoc.springboot.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        allowSwagger(http);
        configureUser(http);
        http
                .cors().and().csrf().disable().authorizeRequests()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(jwtConfiguration);
    }

    /**
     * allow access without login
     *
     * @param http
     * @throws Exception
     */
    private void configureUser(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/api/auth/sign-up",
                        "/api/auth/login",
                        "/api/products",
                        "/api/products/*",
                        "/api/v1/products/*",
                        "/api/v2/products/*",
                        "/api/search",
                        "/api/brand",
                        "/api/brand/*",
                        "/api/category",
                        "/api/category/*",
                        "/api/order")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    /**
     * allow access swagger
     **/
    private void allowSwagger(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**/*swagger*/**", "/v2/api-docs").permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
