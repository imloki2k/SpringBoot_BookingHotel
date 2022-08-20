package com.example.bookinghotel.configuration;



import com.example.bookinghotel.services.UserService;
import com.example.bookinghotel.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DataSource dataSource;




    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userService);
               // .passwordEncoder(passwordEncoder());
    }



//    @Autowired
//    public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//
//    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","login","/register","/sendEmail","/checkAvailableHotelByDate").permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                //.antMatchers("/login").access("hasAnyRole('ROLE_USER')")
                .antMatchers("/manageHotels").access("hasRole('ROLE_OWNER')")
                .antMatchers("/saveBooking").access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
                .and().formLogin().failureForwardUrl("/fail_login")
            //  .and().formLogin().successHandler(customSuccessHandler)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

                http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }


}
