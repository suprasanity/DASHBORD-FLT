package com.example.dashboard.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        List<String> list = new ArrayList<>();
        list.add("http://chovy.freeboxos.fr");
        list.add("http://localhost");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(list);
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/*").fullyAuthenticated()
                .and()
                .httpBasic()

        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(ldapAuthenticationProvider());

    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://chovy.freeboxos.fr");
        contextSource.setBase("dc=chovy,dc=freeboxos,dc=fr");
        contextSource.setUserDn("cn=admin,dc=chovy,dc=freeboxos,dc=fr");
        contextSource.setPassword("yann");
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }

    @Bean
    public BindAuthenticator bindAuthenticator() {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource());
        authenticator.setUserSearch(userSearch());
        return authenticator;
    }

    @Bean
    public FilterBasedLdapUserSearch userSearch() {
        return  new FilterBasedLdapUserSearch("ou=people", "(uid={0})", contextSource());
    }

    @Bean
    public LdapAuthenticationProvider ldapAuthenticationProvider() {
        return new LdapAuthenticationProvider(bindAuthenticator());

    }
}
