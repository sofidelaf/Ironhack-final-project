package com.ironhack.edgeservice.security;

import static com.ironhack.edgeservice.security.Constants.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/**/*.js");
        webSecurity.ignoring().antMatchers("/**/*.json");
        webSecurity.ignoring().antMatchers("/**/*.css");
        webSecurity.ignoring().antMatchers("/**/*.svg");
        webSecurity.ignoring().antMatchers("/**/*.ttf");
        webSecurity.ignoring().antMatchers("/**/*.png");
        webSecurity.ignoring().antMatchers("/**/*.gif");
        webSecurity.ignoring().antMatchers("/**/*.ico");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, LOGIN_URL)
                .permitAll()
                .antMatchers(HttpMethod.GET, "/categories").permitAll()
                .antMatchers(HttpMethod.GET, "/articles").permitAll()
                .antMatchers(HttpMethod.GET, "/articles**").permitAll()
                .antMatchers(HttpMethod.GET, "/novelties").permitAll()
                .antMatchers(HttpMethod.GET, "/discounts").permitAll()
                .antMatchers(HttpMethod.POST, "/contact").permitAll()
                .antMatchers("/login").permitAll()
                //.antMatchers("/administration/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

/*    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }*/

    @Configuration
    public class CorsConfig {

        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("http://localhost:4200");
            config.addAllowedHeader("*");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("GET");
            config.addAllowedMethod("PATCH");
            config.addAllowedMethod("DELETE");
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        }
    }
}
