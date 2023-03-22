package com.example.dashboard.conf;

import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(RequestRejectedException.class)
    public ModelAndView handleRequestRejectedException(RequestRejectedException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "The request was rejected " + ex.getMessage());
        return modelAndView;
    }
}
