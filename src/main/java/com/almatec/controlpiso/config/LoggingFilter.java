package com.almatec.controlpiso.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Value("${logging.proxy.enabled:false}")
    private boolean loggingEnabled;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (loggingEnabled) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            
            // Verifica si la solicitud ha pasado por un proxy
            String xForwardedFor = httpRequest.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                logger.info("Solicitud pasó por proxy. Detalles:");
                logger.info("X-Forwarded-For: {}", xForwardedFor);
                logger.info("X-Forwarded-Host: {}", httpRequest.getHeader("X-Forwarded-Host"));
                logger.info("X-Forwarded-Proto: {}", httpRequest.getHeader("X-Forwarded-Proto"));
                logger.info("Via: {}", httpRequest.getHeader("Via"));
                logger.info("X-Real-IP: {}", httpRequest.getHeader("X-Real-IP"));
            }
        }

        chain.doFilter(request, response);
    }
}

