package com.usta.proyecto;

import com.usta.proyecto.handler.LoginSucessHandler;
import com.usta.proyecto.models.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LoginSucessHandler successHandler;

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/Listar/ListarPersonas").hasRole("Admin")
                .antMatchers("/Listar/ListarPagos").hasAnyRole("Admin","Tesorero")
                .antMatchers("/Listar/ListarRegistros").hasAnyRole("Admin","Tesorero")
                .antMatchers("/Listar/ListarRoles").hasAnyRole("Admin")
                .antMatchers("/Listar/ListarDatos_login").hasAnyRole("Admin")
                .antMatchers("/index").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/signupdos").permitAll()
                .antMatchers("/signupdos").permitAll()
                .antMatchers("/layout").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successHandler)
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{

        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
