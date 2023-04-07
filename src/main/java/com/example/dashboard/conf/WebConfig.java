package com.example.dashboard.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
@Profile("!test")
@ComponentScan(basePackages = "com.example.dashboard")
public class WebConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public FilterRegistrationBean <ForwardedHeaderFilter> forwardedHeaderFilter() {
       ForwardedHeaderFilter filter = new ForwardedHeaderFilter();
         FilterRegistrationBean <ForwardedHeaderFilter> bean = new FilterRegistrationBean <>(filter);
         bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        logger.info("Hello world, I have just started up");
    }

}
