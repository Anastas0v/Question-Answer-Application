package com.example.firstproject.security;

import com.example.firstproject.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()

                // .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()

                // .and()
                // .formLogin()
                // .loginPage("/auth/login")
                // .loginProcessingUrl("/auth/login-successful")
                // .usernameParameter("email").passwordParameter("password")
                // .permitAll()

                // .and()
                // .logout()
                // .logoutUrl("/perform_logout")
                // .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable().cors();
        // .and()
        // .logout().invalidateHttpSession(true)
        // .clearAuthentication(true).permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                .antMatchers("/**/*.js", "/**/*.css", "/static/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}