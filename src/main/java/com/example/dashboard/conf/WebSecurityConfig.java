package com.example.dashboard.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
          CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(getListAllowedOrigins());
            configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
            configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin","Access-Control-Allow-Headers","Origin","X-Requested-With","Content-Type","Access-Control-Request-Method","Access-Control-Request-Headers","Authorization"));
            configuration.setMaxAge(Duration.ofMinutes(30));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
    }

    private List<String> getListAllowedOrigins() {
        String[]array = {"http://localhost:80","http://chovy.freeboxos.fr:80"};
        return new ArrayList<>(Arrays.asList(array));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors(withDefaults())
                .headers().frameOptions().disable()
                .contentSecurityPolicy("default-src: 'none';")
                .and()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/*").fullyAuthenticated()
                .and()
                .httpBasic()

        ;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
