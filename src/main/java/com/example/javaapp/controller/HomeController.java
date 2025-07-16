package com.example.javaapp.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;

import com.example.javaapp.dto.RequestInfo;

@RestController
public class HomeController {
    
    @GetMapping("/*")
    public RequestInfo home(HttpServletRequest request) {
        RequestInfo info = new RequestInfo();
        
        // Set basic request information
        info.setPath(request.getRequestURI());
        info.setMethod(request.getMethod());
        info.setQueryString(request.getQueryString());
        info.setRemoteAddress(request.getRemoteAddr());
        
        // Set common headers
        info.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        info.setHost(request.getHeader(HttpHeaders.HOST));
        info.setXForwardedFor(request.getHeader("X-Forwarded-For"));
        
        // Collect all headers
        Map<String, String> headers = Collections.list(request.getHeaderNames())
            .stream()
            .collect(Collectors.toMap(
                headerName -> headerName,
                request::getHeader,
                (v1, v2) -> v1,
                HashMap::new
            ));
        info.setHeaders(headers);
        
        return info;
    }
}
