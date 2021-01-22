package com.test.config;

import com.test.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and()
                .httpBasic();

        /*web
                .httpFirewall(defaultHttpFirewall())
                //  .httpFirewall(allowUrlEncodedSlashHttpFirewall())
                .ignoring()
                .antMatchers(HttpMethod.GET, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.PATCH, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.POST, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.PUT, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.DELETE, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");*/
    }
}
