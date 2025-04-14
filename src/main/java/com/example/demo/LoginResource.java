package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.resource.AbstractResource;

public class LoginResource extends AbstractResource {

    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse response = new ResourceResponse();
        response.setContentType("application/json");

        String username = attributes.getRequest().getPostParameters().getParameterValue("username").toString();
        String password = attributes.getRequest().getPostParameters().getParameterValue("password").toString();

        if ("admin".equals(username) && "password".equals(password)) {
            String token = JwtUtil.generateToken(username);

            // Set JWT token in a secure cookie
            HttpServletResponse httpResponse = (HttpServletResponse) attributes.getResponse().getContainerResponse();
            Cookie jwtCookie = new Cookie("jwt_token", token);
            jwtCookie.setHttpOnly(false);
            jwtCookie.setSecure(false);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(3600); // 1 hour expiration
            httpResponse.addCookie(jwtCookie);

            response.setWriteCallback(new WriteCallback() {
                @Override
                public void writeData(Attributes attributes) {
                    attributes.getResponse().write("{\"success\": true, \"message\": \"Login successful\"}");
                }
            });
        } else {
            response.setWriteCallback(new WriteCallback() {
                @Override
                public void writeData(Attributes attributes) {
                    attributes.getResponse().write("{\"success\": false, \"message\": \"Invalid credentials\"}");
                }
            });
        }

        return response;
    }
}