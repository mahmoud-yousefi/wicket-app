package com.example.demo;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.request.resource.AbstractResource;

public class ValidateTokenResource extends AbstractResource {

    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse response = new ResourceResponse();
        response.setContentType("application/json");

        HttpServletRequest httpRequest = (HttpServletRequest) attributes.getRequest().getContainerRequest();
        Cookie[] cookies = httpRequest.getCookies();
        String token = null;

        if (cookies != null) {
            token = Arrays.stream(cookies)
                    .filter(cookie -> "jwt_token".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }

        if (token != null && JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsernameFromToken(token);
            response.setWriteCallback(new WriteCallback() {
                @Override
                public void writeData(Attributes attributes) {
                    attributes.getResponse().write("{\"authenticated\": true, \"username\": \"" + username + "\"}");
                }
            });
        } else {
            response.setWriteCallback(new WriteCallback() {
                @Override
                public void writeData(Attributes attributes) {
                    attributes.getResponse().write("{\"authenticated\": false}");
                }
            });
        }

        return response;
    }
}